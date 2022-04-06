<p align="center">
	<img src="https://github.com/paulevsGitch/BlueLib/blob/main/bluelib_logo.png" width="200px" height="200px"/>
</p>

A library for Rising World Unity blueprint format.
Library use [published blueprint format](https://forum.rising-world.net/thread/11808-blueprints-format-unity/?postID=87733#post87733).
At this moment library supports only V6 blueprint format (GameVersion 0.4.5).

Library depends on [LZ4 Java](https://github.com/lz4/lz4-java) library.

### Main features:
- BlueprintIO - allows to save and load blueprints from files, streams and buffers;
- Blueprint - class that represent blueprint instance;
- BlueprintElement - construction elements in blueprints;
- BlueprintObject - objects (furniture) in blueprints;
- BlueprintPlant - plants in blueprints;
- LittleEndianDataOutputStream - stream to save data in LittleEndian order.

### Constants:
- BlueprintElementType - contains default construction element types (with names from definitions.db);
- BlueprintObjectType - contains default object types (with names from definitions.db);
- BlueprintPlantType - contains default plant types (with names from definitions.db);
- BlueprintVersion - contains blueprint version IDs.

## Usage
### Creating new blueprint

Each blueprint require some fields to be initialised on startup, these fields
will be initialised with default values if you will use Blueprint.create() static function
from Blueprint class:

```java
Blueprint blueprint = Blueprint.create();
```

After that you can change any required element fields and add/remove elements. Example:

```java
Blueprint blueprint = Blueprint.create(); // Create blueprint
blueprint.name = "MyGreatBlueprint"; // Set bluepint name

// Set blueprint size
blueprint.sizeX = 2;
blueprint.sizeZ = 2;
blueprint.sizeY = 2;

// Creating new element
BlueprintElement element = new BlueprintElement(BlueprintElementType.BLOCK);
element.setPosition(0, 1, 0); // Set element relative position
element.setSize(size, 0.01F, 0.01F); // Set element size
element.setColor(255, 0, 0, 128); // Set element color (red)
element.texture = 800; // Set element texture, 800 = emissive material
blueprint.addElement(element); // Adding element to blueprint

// Save blueprint to file
File outputFile = new File("./MyGreatBlueprint.blueprint");
try {
    BlueprintIO.write(blueprint, outputFile);
}
catch (IOException exception) {
    exception.printStackTrace();
}
```

Objects and Plants have almost same behaviour and constructors as Elements.

### Loading existing blueprint

If you need to load and modify existing blueprint you can do it with BlueprintIO:
```java
File file = new File("./MyGreatBlueprint.blueprint");
Blueprint blueprint = null;
try {
    blueprint = BlueprintIO.read(file);
}
catch (IOException exception) {
    exception.printStackTrace();
}
```

Now you can change it in any way you need and save it with same method as in section above.