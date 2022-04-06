package paulevs.bluelib.blueprint;


import paulevs.bluelib.blueprint.element.BlueprintElement;
import paulevs.bluelib.blueprint.object.BlueprintObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Blueprint {
	private static final BufferedImage EMPTY_IMAGE_SCREEN;
	private static final BufferedImage EMPTY_IMAGE_THUMB;
	
	public final List<BlueprintElement> elements = new ArrayList<>();
	public final List<BlueprintObject> objects = new ArrayList<>();
	public BufferedImage screenshot;
	public BufferedImage thumbnail;
	public byte version;
	public boolean isLegacy;
	public long timestamp;
	public float posX;
	public float posY;
	public float posZ;
	public float sizeX;
	public float sizeY;
	public float sizeZ;
	public String name;
	public String creator;
	public String creatorUID;
	public String world;
	
	public static Blueprint create() {
		Blueprint blueprint = new Blueprint();
		blueprint.version = BlueprintVersion.UNITY_045;
		blueprint.screenshot = EMPTY_IMAGE_SCREEN;
		blueprint.thumbnail = EMPTY_IMAGE_THUMB;
		blueprint.creatorUID = "code";
		blueprint.creator = "code";
		blueprint.world = "code";
		blueprint.timestamp = System.currentTimeMillis() / 1000L;
		return blueprint;
	}
	
	/**
	 * Add new element to this blueprint.
	 * @param element {@link BlueprintElement} to add.
	 */
	public void addElement(BlueprintElement element) {
		elements.add(element);
	}
	
	/**
	 * Add new object to this blueprint.
	 * @param object {@link BlueprintObject} to add.
	 */
	public void addObject(BlueprintObject object) {
		objects.add(object);
	}
	
	static {
		EMPTY_IMAGE_THUMB = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		Graphics g = EMPTY_IMAGE_THUMB.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 64, 64);
		g.fillRect(64, 64, 64, 64);
		g.setColor(Color.MAGENTA);
		g.fillRect(64, 0, 64, 64);
		g.fillRect(0, 64, 64, 64);
		
		EMPTY_IMAGE_SCREEN = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
		g = EMPTY_IMAGE_THUMB.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 256, 256);
		g.fillRect(256, 256, 256, 256);
		g.setColor(Color.MAGENTA);
		g.fillRect(256, 0, 256, 256);
		g.fillRect(0, 256, 256, 256);
	}
}
