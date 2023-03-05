package paulevs.bluelib.blueprint.object;

public class BlueprintObjectType {
	public static final short BRAZIER = 0;
	public static final short DOOR_1 = 1;
	public static final short WOOD_SHELF_1 = 2;
	public static final short CHEST_1 = 3;
	public static final short CHAIR = 4;
	public static final short TABLE = 5;
	public static final short LOG_BENCH = 6;
	public static final short ANIMAL_FEEDER = 7;
	public static final short BIRD_HOUSE = 8;
	public static final short TORO = 9;
	public static final short CANDLE = 10;
	public static final short LANTERN = 11;
	public static final short CAMPFIRE = 12;
	public static final short BONFIRE = 13;
	public static final short FIRE_LOGS = 14;
	public static final short WALL_TORCH = 15;
	public static final short CLOCK = 17;
	public static final short GRANDFATHER_CLOCK = 18;
	public static final short BED_OLD_1 = 20;
	public static final short LADDER = 21;
	public static final short XMAS_TREE = 22;
	public static final short XMAS_WREATH = 23;
	public static final short PRIMITIVE_WORKBENCH = 25;
	public static final short WORKBENCH = 26;
	public static final short WORKBENCH_MODERN = 27;
	public static final short BLUEPRINT_TABLE = 28;
	public static final short ANVIL = 29;
	public static final short CONSTRUCTION_BARRIER = 30;
	public static final short BARRIER_LIGHT = 31;
	public static final short CROWD_BARRIER = 32;
	public static final short MEAT_DRYER = 33;
	public static final short FLOODLIGHT = 34;
	public static final short PRIMITIVE_SHELTER = 35;
	public static final short SHELTER_CANOPY_1 = 36;
	public static final short SHELTER_CANOPY_1_B = 37;
	public static final short TENT_SMALL = 40;
	public static final short TENT_MODERN = 41;
	public static final short STRAW_BED = 43;
	public static final short MATTRESS = 44;
	public static final short PRIMITIVE_FURNACE = 46;
	public static final short BENCH_OLD = 50;
	public static final short SOCCER_GOAL = 51;
	public static final short WOODEN_BARREL = 55;
	public static final short WOODEN_BARREL_OPEN = 56;
	public static final short TROUGH = 57;
	public static final short BULK_HEAD_LAMP_1 = 60;
	public static final short BULK_HEAD_LAMP_2 = 61;
	public static final short STREET_LAMP_1 = 62;
	public static final short CABINET_1 = 64;
	public static final short TRASHCAN_1 = 65;
	public static final short TRASHCAN_2 = 66;
	public static final short SHOOTING_TARGET_1 = 67;
	public static final short SHOOTING_TARGET_2 = 68;
	public static final short WEATHERVANE = 69;
	public static final short GRINDSTONE = 70;
	public static final short GRINDER = 71;
	public static final short SPINNINGWHEEL = 72;
	public static final short TANNING_RACK = 73;
	public static final short JACK_O_LANTERN_1 = 80;
	public static final short JACK_O_LANTERN_2 = 81;
	public static final short SNOWMAN = 82;
	public static final short STANDING_TORCH = 90;
	public static final short CHICKENCOOP = 95;
	
	public static final short LAST_ID = CHICKENCOOP;
	
	private static final String[] NAMES = new String[LAST_ID + 1];
	private static final String UNKNOWN = "unknown";
	
	/**
	 * Get object name. Object name is identical to its name from RisingWorld definitions database.
	 * If object is not known it will return {@code "unknown"} String constant.
	 * @param type type of the object as short.
	 * @return {@link String} object name.
	 */
	public static String getObjectName(int type) {
		if (type < 0 || type > LAST_ID) return UNKNOWN;
		String name = NAMES[type];
		return name == null ? UNKNOWN : name;
	}
	
	/**
	 * Check if object of this type has name.
	 * @param type type of the object as short.
	 * @return {@code true} if there is a name for object.
	 */
	public static boolean isKnownObject(int type) {
		if (type < 0 || type > LAST_ID) return false;
		return NAMES[type] != null;
	}
	
	static {
		NAMES[BRAZIER] = "brazier";
		NAMES[DOOR_1] = "door_1";
		NAMES[WOOD_SHELF_1] = "wood_shelf_1";
		NAMES[CHEST_1] = "chest_1";
		NAMES[CHAIR] = "chair";
		NAMES[TABLE] = "table";
		NAMES[LOG_BENCH] = "log_bench";
		NAMES[ANIMAL_FEEDER] = "animal_feeder";
		NAMES[BIRD_HOUSE] = "bird_house";
		NAMES[TORO] = "toro";
		NAMES[CANDLE] = "candle";
		NAMES[LANTERN] = "lantern";
		NAMES[CAMPFIRE] = "campfire";
		NAMES[BONFIRE] = "bonfire";
		NAMES[FIRE_LOGS] = "fire_logs";
		NAMES[WALL_TORCH] = "wall_torch";
		NAMES[CLOCK] = "clock";
		NAMES[GRANDFATHER_CLOCK] = "grandfather_clock";
		NAMES[BED_OLD_1] = "bed_old_1";
		NAMES[LADDER] = "ladder";
		NAMES[XMAS_TREE] = "xmas_tree";
		NAMES[XMAS_WREATH] = "xmas_wreath";
		NAMES[PRIMITIVE_WORKBENCH] = "primitive_workbench";
		NAMES[WORKBENCH] = "workbench";
		NAMES[WORKBENCH_MODERN] = "workbench_modern";
		NAMES[BLUEPRINT_TABLE] = "blueprint_table";
		NAMES[ANVIL] = "anvil";
		NAMES[CONSTRUCTION_BARRIER] = "construction_barrier";
		NAMES[BARRIER_LIGHT] = "barrier_light";
		NAMES[CROWD_BARRIER] = "crowd_barrier";
		NAMES[MEAT_DRYER] = "meat_dryer";
		NAMES[FLOODLIGHT] = "floodlight";
		NAMES[PRIMITIVE_SHELTER] = "primitive_shelter";
		NAMES[SHELTER_CANOPY_1] = "shelter_canopy_1";
		NAMES[SHELTER_CANOPY_1_B] = "shelter_canopy_1_b";
		NAMES[TENT_SMALL] = "tent_small";
		NAMES[TENT_MODERN] = "tent_modern";
		NAMES[STRAW_BED] = "straw_bed";
		NAMES[MATTRESS] = "mattress";
		NAMES[PRIMITIVE_FURNACE] = "primitive_furnace";
		NAMES[BENCH_OLD] = "bench_old";
		NAMES[SOCCER_GOAL] = "soccer_goal";
		NAMES[WOODEN_BARREL] = "wooden_barrel";
		NAMES[WOODEN_BARREL_OPEN] = "wooden_barrel_open";
		NAMES[TROUGH] = "trough";
		NAMES[BULK_HEAD_LAMP_1] = "bulk_head_lamp_1";
		NAMES[BULK_HEAD_LAMP_2] = "bulk_head_lamp_2";
		NAMES[STREET_LAMP_1] = "street_lamp_1";
		NAMES[CABINET_1] = "cabinet_1";
		NAMES[TRASHCAN_1] = "trashcan_1";
		NAMES[TRASHCAN_2] = "trashcan_2";
		NAMES[SHOOTING_TARGET_1] = "shooting_target_1";
		NAMES[SHOOTING_TARGET_2] = "shooting_target_2";
		NAMES[WEATHERVANE] = "weathervane";
		NAMES[GRINDSTONE] = "grindstone";
		NAMES[GRINDER] = "grinder";
		NAMES[SPINNINGWHEEL] = "spinningwheel";
		NAMES[TANNING_RACK] = "tanning_rack";
		NAMES[JACK_O_LANTERN_1] = "jack_o_lantern_1";
		NAMES[JACK_O_LANTERN_2] = "jack_o_lantern_2";
		NAMES[SNOWMAN] = "snowman";
		NAMES[STANDING_TORCH] = "standing_torch";
		NAMES[CHICKENCOOP] = "chickencoop";
	}
}
