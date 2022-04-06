package paulevs.bluelib.blueprint.object;

import java.util.HashMap;
import java.util.Map;

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
	
	private static final Map<Short, String> NAMES = new HashMap<>();
	private static final String UNKNOWN = "unknown";
	
	/**
	 * Get object name. Object name is identical to its name from RisingWorld definitions database.
	 * If object is not known it will return {@code "unknown"} String constant.
	 * @param type type of the object as short.
	 * @return {@link String} object name.
	 */
	public static String getObjectName(short type) {
		return NAMES.getOrDefault(type, UNKNOWN);
	}
	
	static {
		NAMES.put(BRAZIER, "brazier");
		NAMES.put(DOOR_1, "door1");
		NAMES.put(WOOD_SHELF_1, "woodshelf1");
		NAMES.put(CHEST_1, "chest1");
		NAMES.put(CHAIR, "chair");
		NAMES.put(TABLE, "table");
		NAMES.put(LOG_BENCH, "logbench");
		NAMES.put(ANIMAL_FEEDER, "animalfeeder");
		NAMES.put(BIRD_HOUSE, "birdhouse");
		NAMES.put(TORO, "toro");
		NAMES.put(CANDLE, "candle");
		NAMES.put(LANTERN, "lantern");
		NAMES.put(CAMPFIRE, "campfire");
		NAMES.put(BONFIRE, "bonfire");
		NAMES.put(FIRE_LOGS, "firelogs");
		NAMES.put(WALL_TORCH, "walltorch");
		NAMES.put(CLOCK, "clock");
		NAMES.put(GRANDFATHER_CLOCK, "grandfatherclock");
		NAMES.put(BED_OLD_1, "bedold1");
		NAMES.put(LADDER, "ladder");
		NAMES.put(XMAS_TREE, "xmastree");
		NAMES.put(XMAS_WREATH, "xmaswreath");
		NAMES.put(PRIMITIVE_WORKBENCH, "primitiveworkbench");
		NAMES.put(WORKBENCH, "workbench");
		NAMES.put(WORKBENCH_MODERN, "workbenchmodern");
		NAMES.put(BLUEPRINT_TABLE, "blueprinttable");
		NAMES.put(ANVIL, "anvil");
		NAMES.put(CONSTRUCTION_BARRIER, "constructionbarrier");
		NAMES.put(BARRIER_LIGHT, "barrierlight");
		NAMES.put(CROWD_BARRIER, "crowdbarrier");
		NAMES.put(MEAT_DRYER, "meatdryer");
		NAMES.put(FLOODLIGHT, "floodlight");
		NAMES.put(PRIMITIVE_SHELTER, "primitiveshelter");
		NAMES.put(SHELTER_CANOPY_1, "sheltercanopy1");
		NAMES.put(SHELTER_CANOPY_1_B, "sheltercanopy1b");
		NAMES.put(TENT_SMALL, "tentsmall");
		NAMES.put(TENT_MODERN, "tentmodern");
		NAMES.put(STRAW_BED, "strawbed");
		NAMES.put(MATTRESS, "mattress");
		NAMES.put(PRIMITIVE_FURNACE, "primitivefurnace");
		NAMES.put(BENCH_OLD, "benchold");
		NAMES.put(SOCCER_GOAL, "soccergoal");
		NAMES.put(WOODEN_BARREL, "woodenbarrel");
		NAMES.put(WOODEN_BARREL_OPEN, "woodenbarrelopen");
		NAMES.put(TROUGH, "trough");
		NAMES.put(BULK_HEAD_LAMP_1, "bulkheadlamp1");
		NAMES.put(BULK_HEAD_LAMP_2, "bulkheadlamp2");
		NAMES.put(STREET_LAMP_1, "streetlamp1");
		NAMES.put(CABINET_1, "cabinet1");
		NAMES.put(TRASHCAN_1, "trashcan1");
		NAMES.put(TRASHCAN_2, "trashcan2");
		NAMES.put(SHOOTING_TARGET_1, "shootingtarget1");
		NAMES.put(SHOOTING_TARGET_2, "shootingtarget2");
	}
}
