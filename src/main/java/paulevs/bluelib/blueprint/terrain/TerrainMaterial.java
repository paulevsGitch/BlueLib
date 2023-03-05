package paulevs.bluelib.blueprint.terrain;

import java.util.HashMap;
import java.util.Map;

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
	
	public static final byte LAST_ID = COBBLE;
	
	private static final String[] NAMES = new String[LAST_ID + 1];
	private static final String UNKNOWN = "air";
	
	public static String getMaterialName(int type) {
		if (type < 0 || type > LAST_ID) return UNKNOWN;
		return NAMES[type];
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
