package test;

import paulevs.bluelib.blueprint.Blueprint;
import paulevs.bluelib.blueprint.BlueprintIO;
import paulevs.bluelib.blueprint.terrain.BlueprintTerrain;

import java.io.File;
import java.io.IOException;

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
		
		File file = new File("D:/SteamLibrary/steamapps/common/RisingWorld/_New Version/Blueprints/Terrain.blueprint");
		Blueprint blueprint = Blueprint.create();
		blueprint.terrain = new BlueprintTerrain(1024, 4, 4);
		blueprint.setSize(200, 2, 4);
		for (int x = 0; x < 1024; x++) {
			for (int y = 0; y < 2; y++) {
				for (int z = 0; z < 4; z++) {
					//blueprint.terrain.setData(x, y, z, (x % 10) * 10, x / 10);
					blueprint.terrain.setMaterial(x, y, z, x / 4);
				}
			}
		}
		BlueprintIO.write(blueprint, file);
		
		blueprint = BlueprintIO.read(file);
		System.out.println(blueprint.terrain);
	}
}
