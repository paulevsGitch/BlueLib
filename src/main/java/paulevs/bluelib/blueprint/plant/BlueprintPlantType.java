package paulevs.bluelib.blueprint.plant;

import java.util.HashMap;
import java.util.Map;

public class BlueprintPlantType {
	public static final short HICKORY = 0;
	public static final short ROCK = 1;
	public static final short IRON_ORE = 2;
	
	private static final Map<Short, String> NAMES = new HashMap<>();
	private static final String UNKNOWN = "unknown";
	
	/**
	 * Get plant name. Plant name is identical to its name from RisingWorld definitions database.
	 * If plant is not known it will return {@code "unknown"} String constant.
	 * @param type type of the plant as short.
	 * @return {@link String} plant name.
	 */
	public static String getPlantName(short type) {
		return NAMES.getOrDefault(type, UNKNOWN);
	}
	
	/**
	 * Check if plant of this type has name.
	 * @param type type of the plant as short.
	 * @return {@code true} if there is a name for plant.
	 */
	public static boolean isKnownPlant(short type) {
		return NAMES.containsKey(type);
	}
	
	static {
		NAMES.put(HICKORY, "hickory");
		NAMES.put(ROCK, "rock");
		NAMES.put(IRON_ORE, "ironore");
	}
}
