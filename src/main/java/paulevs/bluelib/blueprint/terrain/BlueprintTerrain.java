package paulevs.bluelib.blueprint.terrain;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BlueprintTerrain {
	private byte[] materials;
	private byte[] strength;
	private int sizeX;
	private int sizeY;
	private int sizeZ;
	
	public BlueprintTerrain(int sizeX, int sizeY, int sizeZ) {
		setSize(sizeX, sizeY, sizeZ);
	}
	
	/**
	 * Construct object from buffer data.
	 * @param buffer input {@link ByteBuffer} with object data.
	 */
	public BlueprintTerrain(ByteBuffer buffer) {
		/**
		 * bytes   data
		 *     4 - terrain array size x
		 *     4 - terrain array size y
		 *     4 - terrain array size z
		 *     2 * size x * size y * size z - terrain data array (short[]), 8 bits = strength, 8 bits = id
		 */
		int x = buffer.getInt();
		int y = buffer.getInt();
		int z = buffer.getInt();
		setSize(x, y, z);
		buffer.getInt();
		for (int i = 0; i < materials.length; i++) {
			materials[i] = buffer.get();
			strength[i] = buffer.get();
		}
	}
	
	/**
	 * Write object data into buffer
	 * @param buffer {@link ByteBuffer} to write object data into.
	 */
	public void write(ByteBuffer buffer) {
		buffer.putInt(getCapacity());
		buffer.putInt(sizeX);
		buffer.putInt(sizeY);
		buffer.putInt(sizeZ);
		buffer.putInt(materials.length);
		for (int i = 0; i < materials.length; i++) {
			buffer.put(materials[i]);
			buffer.put(strength[i]);
		}
	}
	
	/**
	 * Change terrain size. Will reset data inside terrain
	 * @param x size on X axis
	 * @param y size on Y axis
	 * @param z size on Z axis
	 */
	public void setSize(int x, int y, int z) {
		this.sizeX = x;
		this.sizeY = y;
		this.sizeZ = z;
		int capacity = x * y * z;
		materials = new byte[capacity];
		strength = new byte[capacity];
	}
	
	private int getIndex(int x, int y, int z) {
		return (y * sizeZ + z) * sizeX + x;
	}
	
	private boolean isOutside(int x, int y, int z) {
		return x < 0 || x >= sizeX || y < 0 || y >= sizeY || z < 0 || z >= sizeZ;
	}
	
	public void setStrength(int x, int y, int z, int strength) {
		if (isOutside(x, y, z)) return;
		this.strength[getIndex(x, y, z)] = (byte) Math.max(0, Math.min(strength, 100));
	}
	
	public void setMaterial(int x, int y, int z, int material) {
		if (isOutside(x, y, z)) return;
		materials[getIndex(x, y, z)] = (byte) material;
	}
	
	public void setData(int x, int y, int z, int strength, int material) {
		if (isOutside(x, y, z)) return;
		int index = getIndex(x, y, z);
		this.strength[index] = (byte) Math.max(0, Math.min(strength, 100));
		materials[index] = (byte) material;
	}
	
	public int getMaterial(int x, int y, int z) {
		if (isOutside(x, y, z)) return 0;
		return materials[getIndex(x, y, z)] & 255;
	}
	
	public int getStrength(int x, int y, int z) {
		if (isOutside(x, y, z)) return 0;
		return strength[getIndex(x, y, z)];
	}
	
	public int getCapacity() {
		return materials.length * 2 + 16;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		builder.append("sizeX: "); builder.append(sizeX);
		builder.append(", sizeY: "); builder.append(sizeY);
		builder.append(", sizeZ: "); builder.append(sizeZ);
		builder.append(", strength: "); builder.append(Arrays.toString(strength));
		builder.append(", materials: "); builder.append(Arrays.toString(materials));
		builder.append("]");
		return builder.toString();
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public int getSizeZ() {
		return sizeZ;
	}
}
