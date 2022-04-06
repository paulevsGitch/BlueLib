package paulevs.bluelib.blueprint.element;

import java.util.HashMap;
import java.util.Map;

public class BlueprintElementType {
	public static final byte BLOCK = 0;
	public static final byte CYLINDER = 1;
	public static final byte HALF_CYLINDER = 2;
	public static final byte RAMP = 10;
	public static final byte RAMP_CORNER = 11;
	public static final byte RAMP_CORNER_HALF = 12;
	public static final byte RAMP_CORNER_INNER = 13;
	public static final byte ARC = 15;
	public static final byte ARC_INVERSE = 16;
	public static final byte ARC_CORNER = 17;
	public static final byte ARC_CORNER_INNER = 18;
	public static final byte ARC_INVERSE_CORNER = 19;
	public static final byte ARC_INVERSE_CORNER_INNER = 20;
	public static final byte STAIR1 = 30;
	public static final byte STAIR2 = 31;
	public static final byte STAIR3 = 32;
	public static final byte STAIR1_CORNER = 35;
	public static final byte STAIR1_CORNER_INNER = 36;
	public static final byte TRIANGLE = 40;
	public static final byte PYRAMID = 41;
	public static final byte CONE = 42;
	public static final byte HALF_CONE = 43;
	public static final byte QUARTER_CONE = 44;
	public static final byte HOLLOW_CYLINDER = 50;
	public static final byte HOLLOW_CYLINDER_HALF = 51;
	public static final byte HOLLOW_CYLINDER_CORNER = 52;
	public static final byte PILLAR = 70;	
	public static final byte ROUNDED_BLOCK = 80;	
	public static final byte SPHERE = 81;	
	public static final byte PANE = 90;	
	public static final byte PANE_CIRCLE = 91;	
	public static final byte PANE_HALF_CIRCLE = 92;	
	public static final byte PANE_QUARTER_CIRCLE = 93;	
	public static final byte PANE_TRIANGLE = 95;	
	public static final byte PANE_RIGHT_TRIANGLE = 96;
	public static final byte WINDOW1 = 100;
	public static final byte WINDOW2 = 101;
	public static final byte WINDOW3 = 102;
	public static final byte WINDOW4 = 103;
	public static final byte WINDOW5 = 104;
	public static final byte WINDOW6 = 105;
	public static final byte WINDOW7 = 106;
	public static final byte WINDOW8 = 107;
	public static final byte WINDOW9 = 108;
	public static final byte WINDOW10 = 109;
	
	private static final Map<Byte, String> NAMES = new HashMap<>();
	private static final String UNKNOWN = "unknown";
	
	/**
	 * Get element name. Element name is identical to its name from RisingWorld definitions database.
	 * If element is not known it will return {@code "unknown"} String constant.
	 * @param type type of the element as byte.
	 * @return {@link String} element name.
	 */
	public static String getElementName(byte type) {
		return NAMES.getOrDefault(type, UNKNOWN);
	}
	
	static {
		NAMES.put(BLOCK, "block");
		NAMES.put(CYLINDER, "cylinder");
		NAMES.put(HALF_CYLINDER, "halfcylinder");
		NAMES.put(RAMP, "ramp");
		NAMES.put(RAMP_CORNER, "rampcorner");
		NAMES.put(RAMP_CORNER_HALF, "rampcornerhalf");
		NAMES.put(RAMP_CORNER_INNER, "rampcornerinner");
		NAMES.put(ARC, "arc");
		NAMES.put(ARC_INVERSE, "arcinverse");
		NAMES.put(ARC_CORNER, "arccorner");
		NAMES.put(ARC_CORNER_INNER, "arccornerinner");
		NAMES.put(ARC_INVERSE_CORNER, "arcinversecorner");
		NAMES.put(ARC_INVERSE_CORNER_INNER, "arcinversecornerinner");
		NAMES.put(STAIR1, "stair1");
		NAMES.put(STAIR2, "stair2");
		NAMES.put(STAIR3, "stair3");
		NAMES.put(STAIR1_CORNER, "stair1corner");
		NAMES.put(STAIR1_CORNER_INNER, "stair1cornerinner");
		NAMES.put(TRIANGLE, "triangle");
		NAMES.put(PYRAMID, "pyramid");
		NAMES.put(CONE, "cone");
		NAMES.put(HALF_CONE, "halfcone");
		NAMES.put(QUARTER_CONE, "_quartercone");
		NAMES.put(HOLLOW_CYLINDER, "hollowcylinder");
		NAMES.put(HOLLOW_CYLINDER_HALF, "hollowcylinderhalf");
		NAMES.put(HOLLOW_CYLINDER_CORNER, "hollowcylindercorner");
		NAMES.put(PILLAR, "pillar");
		NAMES.put(ROUNDED_BLOCK, "roundedblock");
		NAMES.put(SPHERE, "sphere");
		NAMES.put(PANE, "pane");
		NAMES.put(PANE_CIRCLE, "panecircle");
		NAMES.put(PANE_HALF_CIRCLE, "panehalfcircle");
		NAMES.put(PANE_QUARTER_CIRCLE, "panequartercircle");
		NAMES.put(PANE_TRIANGLE, "panetriangle");
		NAMES.put(PANE_RIGHT_TRIANGLE, "panerighttriangle");
		NAMES.put(WINDOW1, "window1");
		NAMES.put(WINDOW2, "window2");
		NAMES.put(WINDOW3, "window3");
		NAMES.put(WINDOW4, "window4");
		NAMES.put(WINDOW5, "window5");
		NAMES.put(WINDOW6, "window6");
		NAMES.put(WINDOW7, "window7");
		NAMES.put(WINDOW8, "window8");
		NAMES.put(WINDOW9, "window9");
		NAMES.put(WINDOW10, "window10");
	}
}
