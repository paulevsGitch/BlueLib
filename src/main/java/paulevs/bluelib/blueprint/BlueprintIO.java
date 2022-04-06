package paulevs.bluelib.blueprint;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import paulevs.bluelib.blueprint.element.BlueprintElement;
import paulevs.bluelib.blueprint.object.BlueprintObject;
import paulevs.bluelib.stream.LittleEndianDataOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BlueprintIO {
	private static final LZ4Factory FACTORY = LZ4Factory.fastestInstance();
	
	/**
	 * Read blueprint from file. Will throw an exception if file is missing or is not a blueprint.
	 * @param file {@link File} to load blueprint from.
	 * @return new {@link Blueprint} instance.
	 * @throws IOException
	 */
	public static Blueprint read(File file) throws IOException {
		return read(new FileInputStream(file));
	}
	
	/**
	 * Read blueprint from resources. Will throw an exception if file is missing or is not a blueprint.
	 * @param path {@link String} resource path to load blueprint.
	 * @return new {@link Blueprint} instance.
	 * @throws IOException
	 */
	public static Blueprint read(String path) throws IOException {
		return read(BlueprintIO.class.getResourceAsStream(path));
	}
	
	/**
	 * Read blueprint from input stream.
	 * @param stream {@link InputStream} to read blueprint from. Will be closed after process finish.
	 * @return new {@link Blueprint} instance.
	 * @throws IOException
	 */
	public static Blueprint read(InputStream stream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytes;
		
		while ((bytes = stream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, bytes);
		}
		
		outputStream.flush();
		byte[] data = outputStream.toByteArray();
		outputStream.close();
		stream.close();
		
		ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
		byteBuffer.rewind();
		return read(byteBuffer);
	}
	
	/**
	 * Read blueprint from byte buffer.
	 * @param buffer {@link ByteBuffer} with blueprint data.
	 * @return new {@link Blueprint} instance.
	 * @throws IOException
	 */
	public static Blueprint read(ByteBuffer buffer) throws IOException {
		Blueprint blueprint = new Blueprint();
		
		// Reading Header //
		
		/**
		 * bytes   data
		 *     1 - blueprint version
		 *     4 - total number of bytes in blueprint data
		 *     4 - start position of meta data in buffer
		 *     4 - length of meta data (in bytes)
		 *     4 - start position of thumbnail image in buffer
		 *     4 - length of thumbnail data (in bytes)
		 *     4 - start position of screenshot data in buffer
		 *     4 - length of screenshot data (in bytes)
		 *     4 - start position of actual blueprint data in buffer
		 *     4 - length of actual blueprint data (in bytes)
		 */
		
		blueprint.version = buffer.get();
		
		if (blueprint.version == BlueprintVersion.JAVA_FIRST_BYTE) {
			blueprint.version = buffer.get();
			if (blueprint.version == BlueprintVersion.JAVA_SECOND_BYTE) {
				throw new RuntimeException("Blueprint was saved on Java game version and is not supported");
			}
		}
		
		if (blueprint.version != BlueprintVersion.UNITY_045) {
			throw new RuntimeException("Blueprint version is " + blueprint.version + ", supported version is " + BlueprintVersion.UNITY_045);
		}
		
		int bytes = buffer.getInt();
		int capacity = buffer.capacity();
		if (bytes != capacity) {
			throw new RuntimeException("Blueprint stream size is " + capacity + ", but required: " + bytes);
		}
		
		int metaStart = buffer.getInt();
		int metaLength = buffer.getInt();
		int thumbnailStart = buffer.getInt();
		int thumbnailLength = buffer.getInt();
		int screenshotStart = buffer.getInt();
		int screenshotLength = buffer.getInt();
		int blueprintStart = buffer.getInt();
		int blueprintLength = buffer.getInt();
		
		// Reading Meta //
		
		/**
		 * bytes    data
		 *     1  - is legacy blueprint (from Java version)?
		 *     8  - blueprint creation date (unix timestamp)
		 *     4  - relative x coordinate of the blueprint center (float)
		 *     4  - relative y coordinate of the blueprint center(float)
		 *     4  - relative z coordinate of the blueprint center(float)
		 *     4  - extent/size of the blueprint along x axis(float)
		 *     4  - extent/size of the blueprint along y axis(float)
		 *     4  - extent/size of the blueprint along z axis(float)
		 * 2 + xx - blueprint name (string*)
		 * 2 + xx - creator name (string*)
		 * 2 + xx - creator uid (string*)
		 * 2 + xx - name of world where blueprint was created (string*)
		 */
		
		buffer.position(metaStart);
		blueprint.isLegacy = readBoolean(buffer);
		blueprint.timestamp = buffer.getLong();
		
		blueprint.posX = buffer.getFloat();
		blueprint.posY = buffer.getFloat();
		blueprint.posZ = buffer.getFloat();
		
		blueprint.sizeX = buffer.getFloat();
		blueprint.sizeY = buffer.getFloat();
		blueprint.sizeZ = buffer.getFloat();
		
		blueprint.name = readString(buffer);
		blueprint.creator = readString(buffer);
		blueprint.creatorUID = readString(buffer);
		blueprint.world = readString(buffer);
		
		// Reading Images //
		
		blueprint.screenshot = readImage(buffer, screenshotStart);
		blueprint.thumbnail = readImage(buffer, thumbnailStart);
		
		// Reading Content //
		
		/**
		 * bytes - data
		 *     4 - number of bytes of compressed blueprint data
		 *     4 - uncompressed data length
		 *    xx - LZ4 compressed blueprint data
		 */
		
		buffer.position(blueprintStart);
		int compressedLength = buffer.getInt() - 4;
		int uncompressedLength = buffer.getInt();
		
		byte[] restored = new byte[uncompressedLength];
		byte[] compressed = new byte[compressedLength];
		buffer.get(compressed);
		decompress(compressed, restored);
		
		/**
		 * bytes    data
		 *     4  - number of construction elements in blueprint
		 *    xx  - serialized construction data (see below)
		 *     4  - number of object elements in blueprint
		 *    xx  - serialized object data (see below)
		 *     4  - number of plants in blueprint
		 *     xx - serialized plant data (see below)
		 *     4  - terrain data length (currently unused)
		 *     xx - serialized terrain data (currently unused)
		 */
		
		buffer = ByteBuffer.wrap(restored).order(ByteOrder.LITTLE_ENDIAN);
		buffer.rewind();
		
		// Read construction elements //
		int count = buffer.getInt();
		for (int i = 0; i < count; i++) {
			blueprint.addElement(new BlueprintElement(buffer));
		}
		
		// Read elements (objects/furniture) //
		count = buffer.getInt();
		for (int i = 0; i < count; i++) {
			blueprint.addObject(new BlueprintObject(buffer));
		}
		
		return blueprint;
	}
	
	/**
	 * Write blueprint into file.
	 * @param blueprint {@link Blueprint} to write.
	 * @param file {@link File} to write blueprint into.
	 * @throws IOException
	 */
	public static void write(Blueprint blueprint, File file) throws IOException {
		byte[] data = toArray(blueprint);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(data);
		fileOutputStream.flush();
		fileOutputStream.close();
	}
	
	/**
	 * Convert blueprint into byte array.
	 * @param blueprint {@link Blueprint} to convert.
	 * @return byte array with blueprint data.
	 * @throws IOException
	 */
	public static byte[] toArray(Blueprint blueprint) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		LittleEndianDataOutputStream stream = new LittleEndianDataOutputStream(byteStream);
		
		// Writing Header Filler //
		
		stream.writeByte(blueprint.version);
		for (byte i = 0; i < 9; i++) {
			stream.writeInt(0); // Value will be updated later
		}
		
		// Writing Meta //
		
		/**
		 * bytes    data
		 *     1  - is legacy blueprint (from Java version)?
		 *     8  - blueprint creation date (unix timestamp)
		 *     4  - relative x coordinate of the blueprint center (float)
		 *     4  - relative y coordinate of the blueprint center(float)
		 *     4  - relative z coordinate of the blueprint center(float)
		 *     4  - extent/size of the blueprint along x axis(float)
		 *     4  - extent/size of the blueprint along y axis(float)
		 *     4  - extent/size of the blueprint along z axis(float)
		 * 2 + xx - blueprint name (string*)
		 * 2 + xx - creator name (string*)
		 * 2 + xx - creator uid (string*)
		 * 2 + xx - name of world where blueprint was created (string*)
		 */
		
		int metaStart = stream.getSize();
		
		stream.writeBoolean(blueprint.isLegacy);
		stream.writeLong(blueprint.timestamp);
		stream.writeFloat(blueprint.posX);
		stream.writeFloat(blueprint.posY);
		stream.writeFloat(blueprint.posZ);
		stream.writeFloat(blueprint.sizeX);
		stream.writeFloat(blueprint.sizeY);
		stream.writeFloat(blueprint.sizeZ);
		writeString(stream, blueprint.name);
		writeString(stream, blueprint.creator);
		writeString(stream, blueprint.creatorUID);
		writeString(stream, blueprint.world);
		
		int metaLength = stream.getSize() - metaStart;
		
		// Writing Images //
		
		int thumbnailStart = stream.getSize();
		writeImage(stream, blueprint.thumbnail);
		int thumbnailLength = stream.getSize() - thumbnailStart;
		
		int screenshotStart = stream.getSize();
		writeImage(stream, blueprint.screenshot);
		int screenshotLength = stream.getSize() - screenshotStart;
		
		// Writing Content //
		
		/**
		 * bytes   data
		 *     4 - number of bytes of compressed blueprint data
		 *     4 - uncompressed data length
		 *    xx - LZ4 compressed blueprint data
		 */
		
		/**
		 * LZ4 compressed blueprint data
		 * bytes    data
		 *     4  - number of construction elements in blueprint
		 *    xx  - serialized construction data (see below)
		 *     4  - number of object elements in blueprint
		 *     xx - serialized object data (see below)
		 *     4  - number of plants in blueprint
		 *    xx  - serialized plant data (see below)
		 *     4  - terrain data length (currently unused)
		 *    xx  - serialized terrain data (currently unused)
		 */
		
		int count = blueprint.elements.size();
		int predictedCapacity = 16;
		predictedCapacity += BlueprintElement.BYTES * blueprint.elements.size();
		predictedCapacity += BlueprintObject.BYTES * blueprint.objects.size();
		ByteBuffer dataBuffer = ByteBuffer.allocate(predictedCapacity).order(ByteOrder.LITTLE_ENDIAN);
		dataBuffer.rewind();
		
		dataBuffer.putInt(count);
		for (BlueprintElement element: blueprint.elements) {
			element.write(dataBuffer);
		}
		
		count = blueprint.objects.size();
		dataBuffer.putInt(count);
		for (BlueprintObject object: blueprint.objects) {
			object.write(dataBuffer);
		}
		
		dataBuffer.putInt(0);
		dataBuffer.putInt(0);
		
		int dataStart = stream.getSize();
		byte[] uncompressed = dataBuffer.array();
		byte[] compressed = compress(uncompressed);
		stream.writeInt(compressed.length + 4);
		stream.writeInt(uncompressed.length);
		stream.write(compressed);
		int dataLength = stream.getSize() - dataStart;
		
		// Writing Header //
		
		/**
		 * bytes   data
		 *     1 - blueprint version
		 *     4 - total number of bytes in blueprint data
		 *     4 - start position of meta data in buffer
		 *     4 - length of meta data (in bytes)
		 *     4 - start position of thumbnail image in buffer
		 *     4 - length of thumbnail data (in bytes)
		 *     4 - start position of screenshot data in buffer
		 *     4 - length of screenshot data (in bytes)
		 *     4 - start position of actual blueprint data in buffer
		 *     4 - length of actual blueprint data (in bytes)
		 */
		
		byte[] data = byteStream.toByteArray();
		stream.close();
		byteStream.close();
		
		ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
		
		buffer.position(1);
		buffer.putInt(data.length);
		buffer.putInt(metaStart);
		buffer.putInt(metaLength);
		buffer.putInt(thumbnailStart);
		buffer.putInt(thumbnailLength);
		buffer.putInt(screenshotStart);
		buffer.putInt(screenshotLength);
		buffer.putInt(dataStart);
		buffer.putInt(dataLength);
		
		return data;
	}
	
	/**
	 * Read image from byte buffer.
	 * @param buffer {@link BufferedImage} to read image from.
	 * @param start start index in buffer.
	 * @return loaded {@link BufferedImage}
	 * @throws IOException
	 */
	private static BufferedImage readImage(ByteBuffer buffer, int start) throws IOException {
		/**
		 * bytes	data
		 *      2 - width (pixels)
		 *      2 - height (pixels)
		 * 2 + xx - format (string*), currently always "JPG"
		 *      4 - number of bytes of image data
		 *     xx - image data
		 */
		
		buffer.position(start);
		buffer.getShort(); // short width = buffer.getShort();
		buffer.getShort(); // short height = buffer.getShort();
		readString(buffer); // read format
		int bytes = buffer.getInt();
		
		byte[] data = new byte[bytes];
		buffer.get(data);
		ByteArrayInputStream stream = new ByteArrayInputStream(data);
		BufferedImage img = null;
		if (stream != null) {
			img = ImageIO.read(stream);
			stream.close();
		}
		return img;
	}
	
	/**
	 * Write image into output stream using JPG format (default Rising World Unity image format).
	 * @param stream {@link LittleEndianDataOutputStream} to write string data into.
	 * @param image {@link BufferedImage} to write.
	 * @throws IOException
	 */
	private static void writeImage(LittleEndianDataOutputStream stream, BufferedImage image) throws IOException {
		/**
		 * bytes    data
		 *      2 - width (pixels)
		 *      2 - height (pixels)
		 * 2 + xx - format (string*), currently always "JPG"
		 *      4 - number of bytes of image data
		 *     xx - image data
		 */
		
		stream.writeShort((short) image.getWidth());
		stream.writeShort((short) image.getHeight());
		writeString(stream, "JPG");
		
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		ImageIO.write(image, "JPG", imageStream);
		byte[] data = imageStream.toByteArray();
		imageStream.close();
		
		stream.writeInt(data.length);
		stream.writeBytes(data);
	}
	
	/**
	 * Read string from byte buffer.
	 * @param buffer {@link ByteBuffer} to read string from.
	 * @return {@link String}.
	 */
	private static String readString(ByteBuffer buffer) {
		short length = buffer.getShort();
		if (length == -1) return null;
		char[] chars = new char[length];
		for (short i = 0; i < length; i++) {
			chars[i] = buffer.getChar();
		}
		return new String(chars);
	}
	
	/**
	 * Write string into output stream.
	 * @param stream {@link LittleEndianDataOutputStream} to write string data into.
	 * @param str {@link String} to write.
	 * @throws IOException
	 */
	private static void writeString(LittleEndianDataOutputStream stream, String str) throws IOException {
		if (str == null) {
			stream.writeShort(-1);
			return;
		}
		short length = (short) str.length();
		if (length == 0) {
			stream.writeShort(-1);
			return;
		}
		stream.writeShort(length);
		for (short i = 0; i < length; i++) {
			stream.writeChar(str.charAt(i));
		}
	}
	
	/**
	 * Read boolean from byte buffer.
	 * @param buffer {@link ByteBuffer} to read boolean from.
	 * @return boolean value.
	 */
	private static boolean readBoolean(ByteBuffer buffer) {
		byte data = buffer.get();
		return data > 0;
	}
	
	/**
	 * Decompress byte array data using {@link LZ4FastDecompressor} from internal {@link LZ4Factory}.
	 * @param compressed LZ4 compressed byte array.
	 * @param decompressed decompressed byte array.
	 */
	private static void decompress(byte[] compressed, byte[] decompressed) {
		LZ4FastDecompressor decompressor = FACTORY.fastDecompressor();
		decompressor.decompress(compressed, 0, decompressed, 0, decompressed.length);
	}
	
	/**
	 * Compress byte array data using {@link LZ4Compressor} from internal {@link LZ4Factory}.
	 * @param decompressed decompressed byte array.
	 * @return LZ4 compressed byte array.
	 */
	private static byte[] compress(byte[] decompressed) {
		LZ4Compressor decompressor = FACTORY.fastCompressor();
		return decompressor.compress(decompressed);
	}
}
