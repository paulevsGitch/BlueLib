package paulevs.bluelib.blueprint.plant;

import paulevs.bluelib.blueprint.BlueprintIO;
import paulevs.bluelib.blueprint.object.BlueprintObjectType;

import java.nio.ByteBuffer;

public class BlueprintPlant {
	public static final int BYTES = 47;
	
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
	public boolean isPlantCut;
	public int additionalFlags;
	
	/**
	 * Construct empty object with specified type and variation, with some default values.
	 * @param type object type. See {@link BlueprintObjectType} constants for available built-in element types.
	 */
	public BlueprintPlant(short type) {
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
	public BlueprintPlant(ByteBuffer buffer) {
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
		 *     2 - plant type id
		 *     1 - is plant cut?
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
		isPlantCut = BlueprintIO.readBoolean(buffer);
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
		BlueprintIO.writeBoolean(buffer, isPlantCut);
		buffer.putInt(additionalFlags);
	}
	
	/**
	 * Set object size. Default size is 1.0, 1.0, 1.0.
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
		builder.append(" ("); builder.append(BlueprintPlantType.getPlantName(type)); builder.append(")");
		builder.append(", isPlantCut: "); builder.append(isPlantCut);
		builder.append(", additionalFlags: "); builder.append(additionalFlags);
		builder.append("]");
		return builder.toString();
	}
}
