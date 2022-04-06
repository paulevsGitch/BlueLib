package paulevs.bluelib.blueprint.object;

import java.nio.ByteBuffer;

public class BlueprintObject {
	public static final int BYTES = 63;
	
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
	public short type;
	public int variation;
	public int rgba;
	public byte status;
	public long additionalInfo;
	public int additionalFlags;
	
	/**
	 * Construct empty object with specified type and variation, with some default values.
	 * @param type object type. See {@link BlueprintObjectType} constants for available built-in element types.
	 */
	public BlueprintObject(short type) {
		this.type = type;
		rotW = 1.0F;
		sizeX = 1.0F;
		sizeY = 1.0F;
		sizeZ = 1.0F;
	}
	
	/**
	 * Construct object from buffer data.
	 * @param buffer input {@link ByteBuffer} with object data.
	 */
	public BlueprintObject(ByteBuffer buffer) {
		/**
		 * bytes   data
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
		 *     2 - object type id
		 *     4 - variation
		 *     4 - RGBA color (int)
		 *     1 - status
		 *     8 - additional object info (long)
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
		type = buffer.getShort();
		variation = buffer.getInt();
		rgba = buffer.getInt();
		status = buffer.get();
		additionalInfo = buffer.getLong();
		additionalFlags = buffer.getInt();
	}
	
	/**
	 * Write object data into buffer
	 * @param buffer {@link ByteBuffer} to write object data into.
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
		buffer.putShort(type);
		buffer.putInt(variation);
		buffer.putInt(rgba);
		buffer.put(status);
		buffer.putLong(additionalInfo);
		buffer.putInt(additionalFlags);
	}
	
	/**
	 * Set object color based on 4 float RGBA channel values.
	 * @param r red channel component [0.0 - 1.0].
	 * @param g green channel component [0.0 - 1.0].
	 * @param b blue channel component [0.0 - 1.0].
	 * @param a alpha channel component [0.0 - 1.0].
	 */
	public void setColor(float r, float g, float b, float a) {
		setColor((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
	}
	
	/**
	 * Set object color based on 4 int RGBA channel values.
	 * @param r red channel component [0 - 255].
	 * @param g green channel component [0 - 255].
	 * @param b blue channel component [0 - 255].
	 * @param a alpha channel component [0 - 255].
	 */
	public void setColor(int r, int g, int b, int a) {
		rgba = r << 24 | g << 16 | b << 8 | a;
	}
	
	/**
	 * Set object size.
	 * Default size is 1.0, 1.0, 1.0.
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
	 * Set object position. Position is relative to object center.
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
	 * Set object rotation in quaternion format (Unity). Rotation is applied around object center.
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
		builder.append(" ("); builder.append(BlueprintObjectType.getObjectName(type)); builder.append(")");
		builder.append(", variation: "); builder.append(variation);
		builder.append(", rgba: "); builder.append(rgba);
		builder.append(", status: "); builder.append(status);
		builder.append(", additionalInfo: "); builder.append(additionalInfo);
		builder.append(", additionalFlags: "); builder.append(additionalFlags);
		builder.append("]");
		return builder.toString();
	}
}
