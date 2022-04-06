package paulevs.bluelib.blueprint.object;

import paulevs.bluelib.blueprint.element.BlueprintElementType;

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
	 * @param type element type.
	 * @param variation element variation.
	 */
	public BlueprintObject(short type, int variation) {
		this.type = type;
		this.variation = variation;
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
}
