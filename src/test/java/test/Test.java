package test;

import paulevs.bluelib.blueprint.Blueprint;
import paulevs.bluelib.blueprint.BlueprintIO;
import paulevs.bluelib.blueprint.terrain.BlueprintTerrain;
import paulevs.bluelib.blueprint.terrain.TerrainMaterial;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Test {
	public static void main(String[] args) throws IOException {
		/*File file = new File("D:/SteamLibrary/steamapps/common/RisingWorld/_New Version/Blueprints/TerrainData2_1678017221.blueprint");
		Blueprint blueprint = BlueprintIO.read(file);
		System.out.println(blueprint.terrain);*/
		
		/*for (int x = 0; x < blueprint.terrain.getSizeX(); x++) {
			for (int y = 0; y < blueprint.terrain.getSizeY(); y++) {
				for (int z = 0; z < blueprint.terrain.getSizeZ(); z++) {
					blueprint.terrain.setMaterial(x, y, z, 3);
				}
			}
		}
		
		blueprint.name = "Gravel Cube 2";
		file = new File("D:/SteamLibrary/steamapps/common/RisingWorld/_New Version/Blueprints/gravel_cube_2.blueprint");
		BlueprintIO.write(blueprint, file);*/
		
		/*File file = new File("D:/SteamLibrary/steamapps/common/RisingWorld/_New Version/Blueprints/Terrain2.blueprint");
		Blueprint blueprint = Blueprint.create();
		blueprint.terrain = new BlueprintTerrain(40, 4, 4);
		blueprint.setSize(40, 4, 4);
		for (int x = 0; x < 40; x++) {
			for (int y = 0; y < 4; y++) {
				for (int z = 0; z < 4; z++) {
					blueprint.terrain.setMaterial(x, y, z, x / 4 + 64);
				}
			}
		}
		BlueprintIO.write(blueprint, file);*/
		
		/*blueprint = BlueprintIO.read(file);
		System.out.println(blueprint.terrain);*/
		
		/*Blueprint blueprint = Blueprint.create();
		blueprint.name = "Gaskel";
		blueprint.terrain = new BlueprintTerrain(512, 512, 512);
		blueprint.setSize(512, 512, 512);
		String folder = "D:/Mandelbulb3Dv199sr33/Voxels/Gaskel";
		for (int y = 0; y < 512; y++) {
			BufferedImage img = ImageIO.read(new File(folder, String.format("new%06d.png", y * 2 + 1)));
			BufferedImage img2 = ImageIO.read(new File(folder, String.format("new%06d.png", y * 2 + 2)));
			for (int x = 0; x < 512; x++) {
				for (int z = 0; z < 512; z++) {
					boolean search = true;
					for (int dx = 0; search && dx < 2; dx++) {
						for (int dz = 0; search && dz < 2; dz++) {
							int intensity = img.getRGB(x * 2 + dx, z * 2 + dz) & 255;
							if (intensity > 127) {
								blueprint.terrain.setMaterial(x / 2, y / 2, z / 2, 1);
								search = false;
							}
							else {
								intensity = img2.getRGB(x * 2 + dx, z * 2 + dz) & 255;
								if (intensity > 127) {
									blueprint.terrain.setMaterial(x / 2, y / 2, z / 2, 1);
									search = false;
								}
							}
						}
					}
				}
			}
		}
		
		File file = new File("D:/SteamLibrary/steamapps/common/RisingWorld/_New Version/Blueprints/Gaskel.blueprint");
		BlueprintIO.write(blueprint, file);*/
		
		Blueprint blueprint = Blueprint.create();
		blueprint.name = "Mare Orientale";
		
		BufferedImage heightmap = ImageIO.read(new File("D:/mare_orientale.png"));
		int sideX = heightmap.getWidth();
		int sideZ = heightmap.getHeight();
		
		blueprint.terrain = new BlueprintTerrain(sideX, 64, sideZ);
		blueprint.setSize(heightmap.getWidth(), 64, heightmap.getHeight());
		Random random = new Random(0);
		int waterHeight = 40;
		
		for (int x = 0; x < sideX; x++) {
			for (int z = 0; z < sideZ; z++) {
				int height = (heightmap.getRGB(x, z) & 255) / 4;
				
				for (int y = 0; y < height; y++) {
					blueprint.terrain.setMaterial(x, y, z, TerrainMaterial.STONE);
				}
				
				for (int y = height + 1; y < waterHeight; y++) {
					blueprint.terrain.setMaterial(x, y, z, TerrainMaterial.WATER_INFINITY);
				}
				
				if (height < waterHeight - 5 && random.nextBoolean()) {
					blueprint.terrain.setMaterial(x, height, z, TerrainMaterial.getSeaweedGrass(random.nextInt(5)));
				}
				else if (height < waterHeight - 3) {
					blueprint.terrain.setMaterial(x, height, z, TerrainMaterial.SAND_SEA);
				}
				else if (height < (waterHeight + random.nextInt(2))) {
					blueprint.terrain.setMaterial(x, height, z, TerrainMaterial.SAND_BEACH);
				}
				else {
					blueprint.terrain.setMaterial(x, height, z, TerrainMaterial.getGrass(random.nextInt(5)));
				}
			}
		}
		
		File file = new File("D:/SteamLibrary/steamapps/common/RisingWorld/_New Version/Blueprints/" + blueprint.name + ".blueprint");
		BlueprintIO.write(blueprint, file);
	}
}
