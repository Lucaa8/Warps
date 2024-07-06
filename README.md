# Warps

This simple Spigot plugin allows you to register specific locations in any world on your Minecraft server. These locations, known as warps, can be teleported to later using aliases.

## Version and Dependencies
### Version
The plugin's version is `1.0`. It's designed for Spigot/Paper versions 1.20.x only because of the `SpigotApi` plugin dependency which is working in 1.20.x only.

### Dependency
To use the `Warps` plugin, you must first put the [SpigotApi](https://github.com/Lucaa8/SpigotApi/releases/tag/Release-2.1) plugin in your `plugins` folder. This dependency is crucial as it provides a range of useful methods utilized by `Warps`, particularly the `JSONApi` for reading and writing JSON files containing warp information.

If you're interested in leveraging the `SpigotApi` for your own plugins, visit the [GitHub page](https://github.com/Lucaa8/SpigotApi). There, you can find numerous examples in the `examples` folder to help you get started. The README on the GitHub page also details how to import the API using Maven.

## Commands
The plugin provides four commands that allow you to create, delete, teleport to, and list warp locations. Also note that every command has a tab completer with the warp names to avoid writing long names each time you want to teleport.

### Setwarp Command
#### Overview
This command creates a warp at your current location, capturing:
- X, Y, Z coordinates
- Yaw and Pitch
- World
Anyone teleporting to this warp later will arrive at the exact same location and orientation as when the command was issued.

#### Permission
`warps.set`

#### Command Arguments
This command requires two arguments:
- **name**: Specify a name for your warp. This name will be used to teleport to your warp later.
- **description** (optional): As the **name** does not support spaces and special characters, you can specify an optional description to remember why you created this warp and where it teleports you.

#### Example
Here is an example of usage:
- `/setwarp home2 &aluca's home in the overworld &b(biome mushroom)`

![image](https://github.com/Lucaa8/Warps/assets/47627900/8090c6ba-0718-4678-8eed-2b62bbe61885)


### Unsetwarp Command
#### Overview
The `unsetwarp` command allows you to delete an existing warp. This action is irreversible.

#### Permission
`warps.unset`

#### Command Argument
This command requires one argument:
- **name**: The name of the warp you want to delete.

#### Example
- `/unsetwarp home2`

![image](https://github.com/Lucaa8/Warps/assets/47627900/bf470351-d31a-48da-931f-ab3f753bf196)


### Warp Command
#### Overview
The `warp` command enables you to teleport to any warp created by you or other players on the server.

#### Permission
This command does not require any permissions; it is accessible to everyone on the server.

#### Command Argument
This command requires one argument:
- **name**: The name of the warp to which you want to teleport.

#### Example
- `/warp home`

![image](https://github.com/Lucaa8/Warps/assets/47627900/7c25c4d2-b6ee-4b09-b07d-48aaa0ecce0d)
(You can see tab completion with existing warps)


### Warps Command
#### Overview
This command displays all the warps created on the server. Additionally, it provides some details:
- The name
- The optional description
- Who created the warp
- When it was created

This command also lists the available commands and their arguments for easy reference.

#### Permission
This command does not require any permissions; it is accessible to everyone on the server.

#### Command Argument
This command does not require any arguments.

#### Example
![image](https://github.com/Lucaa8/Warps/assets/47627900/7b069e9e-070f-41d0-a00e-153b8f55bb0b)


