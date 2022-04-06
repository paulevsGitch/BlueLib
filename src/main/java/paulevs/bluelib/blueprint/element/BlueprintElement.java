package paulevs.bluelib.blueprint.element;

import java.nio.ByteBuffer;

public class BlueprintElement {
	public static final int BYTES = 105;
	
	public float posX;
	public float posY;
	public float posZ;
	public float rotX;
	public float rotY;
	public float rotZ;
	public float rotW;
	public float sizeX;
	public float sizeY;
	public float sizeZ;
	public byte type;
	public int texture;
	public int rgba;
	public float surfaceOffsetX;
	public float surfaceOffsetY;
	public float surfaceOffsetZ;
	public float surfaceScaleX;
	public float surfaceScaleY;
	public float surfaceScaleZ;
	public float thickness;
	public float textureScaleFactor;
	public int additionalFlags;
	
	/**
	 * Construct empty element with specified type and with some default values.
	 * @param type element type. See {@link BlueprintElementType} constants for available built-in element types.
	 */
	public BlueprintElement(byte type) {
		this.type = type;
		rotW = 1.0F;
		sizeX = 1.0F;
		sizeY = 1.0F;
		sizeZ = 1.0F;
		surfaceScaleX = 1.0F;
		surfaceScaleY = 1.0F;
		surfaceScaleZ = 1.0F;
		thickness = 1.0F;
		textureScaleFactor = 1.0F;
	}
	
	/**
	 * Construct element from buffer data.
	 * @param buffer input {@link ByteBuffer} with element data.
	 */
	public BlueprintElement(ByteBuffer buffer) {
		/**
		 * bytes - data
		 *     4 - relative x position (float)
		 *     4 - relative y position (float)
		 *     4 - relative z position (float)
		 *     4 - rotation x value (quaternion)
		 *     4 - rotation y value (quaternion)
		 *     4 - rotation z value (quaternion)
		 *     4 - rotation w value (quaternion)
		 *     4 - size along x (float)
		 *     4 - size along y (float)
		 *     4 - size along z (float)
		 *     1 - construction type id
		 *     4 - texture
		 *     4 - RGBA color (int) (unused)
		 *     4 - RGBA color (int) (unused)
		 *     4 - RGBA color (int) (unused)
		 *     4 - RGBA color (int) (unused)
		 *     4 - RGBA color (int) (unused)
		 *     4 - RGBA color (int)
		 *     4 - surface x offset (float)
		 *     4 - surface y offset (float)
		 *     4 - surface z offset (float)
		 *     4 - surface x scale (float)
		 *     4 - surface y scale (float)
		 *     4 - surface z scale (float)
		 *     4 - optional thickness (float)
		 *     4 - texture scale factor (float)
		 *     4 - optional additional flags (int bitmask)
		 */
		
		posX = buffer.getFloat();
		posY = buffer.getFloat();
		posZ = buffer.getFloat();
		rotX = buffer.getFloat();
		rotY = buffer.getFloat();
		rotZ = buffer.getFloat();
		rotW = buffer.getFloat();
		sizeX = buffer.getFloat();
		sizeY = buffer.getFloat();
		sizeZ = buffer.getFloat();
		type = buffer.get();
		texture = buffer.getInt();
		buffer.position(buffer.position() + 20);
		rgba = buffer.getInt();
		surfaceOffsetX = buffer.getFloat();
		surfaceOffsetY = buffer.getFloat();
		surfaceOffsetZ = buffer.getFloat();
		surfaceScaleX = buffer.getFloat();
		surfaceScaleY = buffer.getFloat();
		surfaceScaleZ = buffer.getFloat();
		thickness = buffer.getFloat();
		textureScaleFactor = buffer.getFloat();
		additionalFlags = buffer.getInt();
	}
	
	/**
	 * Write element data into buffer
	 * @param buffer {@link ByteBuffer} to write element data into.
	 */
	public void write(ByteBuffer buffer) {
		buffer.putFloat(posX);
		buffer.putFloat(posY);
		buffer.putFloat(posZ);
		buffer.putFloat(rotX);
		buffer.putFloat(rotY);
		buffer.putFloat(rotZ);
		buffer.putFloat(rotW);
		buffer.putFloat(sizeX);
		buffer.putFloat(sizeY);
		buffer.putFloat(sizeZ);
		buffer.put(type);
		buffer.putInt(texture);
		
		for (short i = 0; i < 6; i++) {
			buffer.putInt(rgba);
		}
		
		buffer.putFloat(surfaceOffsetX);
		buffer.putFloat(surfaceOffsetY);
		buffer.putFloat(surfaceOffsetZ);
		buffer.putFloat(surfaceScaleX);
		buffer.putFloat(surfaceScaleY);
		buffer.putFloat(surfaceScaleZ);
		buffer.putFloat(thickness);
		buffer.putFloat(textureScaleFactor);
		buffer.putInt(additionalFlags);
	}
	
	/**
	 * Set element color based on 4 float RGBA channel values.
	 * @param r red channel component [0.0 - 1.0].
	 * @param g green channel component [0.0 - 1.0].
	 * @param b blue channel component [0.0 - 1.0].
	 * @param a alpha channel component [0.0 - 1.0].
	 */
	public void setColor(float r, float g, float b, float a) {
		setColor((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
	}
	
	/**
	 * Set element color based on 4 int RGBA channel values.
	 * @param r red channel component [0 - 255].
	 * @param g green channel component [0 - 255].
	 * @param b blue channel component [0 - 255].
	 * @param a alpha channel component [0 - 255].
	 */
	public void setColor(int r, int g, int b, int a) {
		rgba = r << 24 | g << 16 | b << 8 | a;
	}
	
	/**
	 * Set element size. Default size is 1.0, 1.0, 1.0.
	 * @param x scale by X axis.
	 * @param y scale by Y axis.
	 * @param z scale by Z axis.
	 */
	public void setSize(float x, float y, float z) {
		sizeX = x;
		sizeY = y;
		sizeZ = z;
	}
	
	/**
	 * Set element position. Position is relative to object center.
	 * Default position is 0.0, 0.0, 0.0.
	 * @param x position on X axis.
	 * @param y position on Y axis.
	 * @param z position on Z axis.
	 */
	public void setPosition(float x, float y, float z) {
		posX = x;
		posY = y;
		posZ = z;
	}
	
	/**
	 * Set element surface offset.
	 * Offset is relative to element itself and will automatically use element rotation and transforms.
	 * @param x offset on X axis.
	 * @param y offset on Y axis.
	 * @param z offset on Z axis.
	 */
	public void setSurfaceOffset(float x, float y, float z) {
		surfaceOffsetX = x;
		surfaceOffsetY = y;
		surfaceOffsetZ = z;
	}
	
	/**
	 * Set element rotation in quaternion format (Unity). Rotation is applied around element center.
	 * @param x quaternion X component.
	 * @param y quaternion Y component.
	 * @param z quaternion Z component.
	 * @param w quaternion W component.
	 */
	public void setRotation(float x, float y, float z, float w) {
		rotX = x;
		rotY = y;
		rotZ = z;
		rotW = w;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		builder.append("posX: "); builder.append(posX);
		builder.append(", posY: "); builder.append(posY);
		builder.append(", posZ: "); builder.append(posZ);
		builder.append(", rotX: "); builder.append(rotX);
		builder.append(", rotY: "); builder.append(rotY);
		builder.append(", rotZ: "); builder.append(rotZ);
		builder.append(", rotW: "); builder.append(rotW);
		builder.append(", sizeX: "); builder.append(sizeX);
		builder.append(", sizeY: "); builder.append(sizeY);
		builder.append(", sizeZ: "); builder.append(sizeZ);
		builder.append(", type: "); builder.append(type);
		builder.append(" ("); builder.append(BlueprintElementType.getElementName(type)); builder.append(")");
		builder.append(", texture: "); builder.append(texture);
		builder.append(", rgba: "); builder.append(rgba);
		builder.append(", surfaceOffsetX: "); builder.append(surfaceOffsetX);
		builder.append(", surfaceOffsetY: "); builder.append(surfaceOffsetY);
		builder.append(", surfaceOffsetZ: "); builder.append(surfaceOffsetZ);
		builder.append(", surfaceScaleX: "); builder.append(surfaceScaleX);
		builder.append(", surfaceScaleY: "); builder.append(surfaceScaleY);
		builder.append(", surfaceScaleZ: "); builder.append(surfaceScaleZ);
		builder.append(", thickness: "); builder.append(thickness);
		builder.append(", textureScaleFactor: "); builder.append(textureScaleFactor);
		builder.append(", additionalFlags: "); builder.append(additionalFlags);
		builder.append("]");
		return builder.toString();
	}
}
