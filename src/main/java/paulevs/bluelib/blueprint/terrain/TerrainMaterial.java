package paulevs.bluelib.blueprint.terrain;

public class TerrainMaterial {
	public static final byte AIR = 0;
	public static final byte STONE = 1;
	public static final byte GRAVEL_1 = 2;
	public static final byte GRAVEL_2 = 3;
	public static final byte GRAVEL_3 = 4;
	public static final byte DIRT = 5;
	public static final byte MUD = 6;
	public static final byte FARMLAND_DRY = 7;
	public static final byte FARMLAND_WET = 8;
	public static final byte FOREST_GROUND_1 = 9;
	public static final byte FOREST_GROUND_2 = 10;
	public static final byte FOREST_GROUND_3 = 11;
	public static final byte FOREST_GRASS = 12;
	public static final byte VOLCANIC = 13;
	public static final byte OBSIDIAN = 14;
	public static final byte OBSIDIAN_HOT = 15;
	public static final byte DRY_GROUND = 16;
	public static final byte SAND_DESERT = 17;
	public static final byte SAND_BEACH = 18;
	public static final byte SAND_SEA = 19;
	public static final byte DESERT_STONE = 20;
	public static final byte SANDSTONE_1 = 21;
	public static final byte SANDSTONE_2 = 22;
	public static final byte RED_CLAY = 23;
	public static final byte RUBBLE = 24;
	public static final byte SNOW = 25;
	public static final byte ICE = 26;
	public static final byte UNDERWATER = 27;
	public static final byte SEA_GRASS = 28;
	public static final byte HELLSTONE = 29;
	public static final byte HELLSTONE_HOT = 30;
	public static final byte COBBLE = 31;
	
	public static final byte COAL_ORE = 50;
	public static final byte SULFUR_ORE = 51;
	public static final byte IRON_ORE = 52;
	public static final byte ALUMINIUM_ORE = 53;
	public static final byte TUNGSTEN_ORE = 54;
	public static final byte GOLD_ORE = 55;
	
	public static final byte GRASS_0 = 100;
	public static final byte GRASS_1 = 101;
	public static final byte GRASS_2 = 102;
	public static final byte GRASS_3 = 103;
	public static final byte GRASS_4 = 104;
	
	public static final byte GRASS_ARID_0 = 105;
	public static final byte GRASS_ARID_1 = 106;
	public static final byte GRASS_ARID_2 = 107;
	public static final byte GRASS_ARID_3 = 108;
	public static final byte GRASS_ARID_4 = 109;
	
	public static final byte GRASS_DRY_0 = 110;
	public static final byte GRASS_DRY_1 = 111;
	public static final byte GRASS_DRY_2 = 112;
	public static final byte GRASS_DRY_3 = 113;
	public static final byte GRASS_DRY_4 = 114;
	
	public static final byte GRASS_DEAD_0 = 115;
	public static final byte GRASS_DEAD_1 = 116;
	public static final byte GRASS_DEAD_2 = 117;
	public static final byte GRASS_DEAD_3 = 118;
	public static final byte GRASS_DEAD_4 = 119;
	
	public static final byte GRASS_FROZEN_0 = 120;
	public static final byte GRASS_FROZEN_1 = 121;
	public static final byte GRASS_FROZEN_2 = 122;
	public static final byte GRASS_FROZEN_3 = 123;
	public static final byte GRASS_FROZEN_4 = 124;
	
	public static final byte GRASS_FOREST_0 = 125;
	public static final byte GRASS_FOREST_1 = 126;
	public static final byte GRASS_FOREST_2 = 127;
	public static final byte GRASS_FOREST_3 = (byte) 128;
	public static final byte GRASS_FOREST_4 = (byte) 129;
	
	public static final byte GRASS_JUNGLE_0 = (byte) 130;
	public static final byte GRASS_JUNGLE_1 = (byte) 131;
	public static final byte GRASS_JUNGLE_2 = (byte) 132;
	public static final byte GRASS_JUNGLE_3 = (byte) 133;
	public static final byte GRASS_JUNGLE_4 = (byte) 134;
	
	public static final byte GRASS_SEA_0 = (byte) 135;
	public static final byte GRASS_SEA_1 = (byte) 136;
	public static final byte GRASS_SEA_2 = (byte) 137;
	public static final byte GRASS_SEA_3 = (byte) 138;
	public static final byte GRASS_SEA_4 = (byte) 139;
	
	public static final byte GRASS_SEAWEED_0 = (byte) 140;
	public static final byte GRASS_SEAWEED_1 = (byte) 141;
	public static final byte GRASS_SEAWEED_2 = (byte) 142;
	public static final byte GRASS_SEAWEED_3 = (byte) 143;
	public static final byte GRASS_SEAWEED_4 = (byte) 144;
	
	public static final byte WATER_FLOWING = (byte) 202;
	public static final byte WATER_INFINITY = (byte) 203;
	
	public static final int LAST_ID = WATER_INFINITY & 255;
	
	private static final String[] NAMES = new String[LAST_ID + 1];
	private static final String UNKNOWN = "air";
	
	public static String getMaterialName(int type) {
		if (type < 0 || type > LAST_ID) return UNKNOWN;
		String name = NAMES[type];
		return name == null ? UNKNOWN : name;
	}
	
	private static byte getAnyGrass(int grass, int length) {
		length = Math.max(0, Math.min(length, 4));
		return (byte) (grass + length);
	}
	
	public static byte getGrass(int length) {
		return getAnyGrass(GRASS_0, length);
	}
	
	public static byte getAridGrass(int length) {
		return getAnyGrass(GRASS_ARID_0, length);
	}
	
	public static byte getDryGrass(int length) {
		return getAnyGrass(GRASS_DRY_0, length);
	}
	
	public static byte getDeadGrass(int length) {
		return getAnyGrass(GRASS_DEAD_0, length);
	}
	
	public static byte getFrozenGrass(int length) {
		return getAnyGrass(GRASS_FROZEN_0, length);
	}
	
	public static byte getForestGrass(int length) {
		return getAnyGrass(GRASS_FOREST_0, length);
	}
	
	public static byte getJungleGrass(int length) {
		return getAnyGrass(GRASS_JUNGLE_0, length);
	}
	
	public static byte getSeaGrass(int length) {
		return getAnyGrass(GRASS_SEA_0, length);
	}
	
	public static byte getSeaweedGrass(int length) {
		return getAnyGrass(GRASS_SEAWEED_0, length);
	}
	
	static {
		NAMES[AIR] = "air";
		NAMES[STONE] = "stone";
		NAMES[GRAVEL_1] = "gravel_1";
		NAMES[GRAVEL_2] = "gravel_2";
		NAMES[GRAVEL_3] = "gravel_3";
		NAMES[DIRT] = "dirt";
		NAMES[MUD] = "mud";
		NAMES[FARMLAND_DRY] = "farmland_dry";
		NAMES[FARMLAND_WET] = "farmland_wet";
		NAMES[FOREST_GROUND_1] = "forest_ground_1";
		NAMES[FOREST_GROUND_2] = "forest_ground_2";
		NAMES[FOREST_GROUND_3] = "forest_ground_3";
		NAMES[FOREST_GRASS] = "forest_grass";
		NAMES[VOLCANIC] = "volcanic";
		NAMES[OBSIDIAN] = "obsidian";
		NAMES[OBSIDIAN_HOT] = "obsidian_hot";
		NAMES[DRY_GROUND] = "dry_ground";
		NAMES[SAND_DESERT] = "sand_desert";
		NAMES[SAND_BEACH] = "sand_beach";
		NAMES[SAND_SEA] = "sand_sea";
		NAMES[DESERT_STONE] = "desert_stone";
		NAMES[SANDSTONE_1] = "sandstone_1";
		NAMES[SANDSTONE_2] = "sandstone_2";
		NAMES[RED_CLAY] = "red_clay";
		NAMES[RUBBLE] = "rubble";
		NAMES[SNOW] = "snow";
		NAMES[ICE] = "ice";
		NAMES[UNDERWATER] = "underwater";
		NAMES[SEA_GRASS] = "sea_grass";
		NAMES[HELLSTONE] = "hellstone";
		NAMES[HELLSTONE_HOT] = "hellstone_hot";
		NAMES[COBBLE] = "cobble";
	}
}
