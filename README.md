# Rarer Spawn Islands

**[Product of Achilleus](https://linktr.ee/achilleus_)** · Maintained by [Achilleus (Achilleus-1)](https://github.com/Achilleus-1).

Reduces how often tropical and volcanic islands generate in Spawn.

## Installation

Requires **Minecraft 1.21.1**, **NeoForge**, **Java 21**, and **Spawn 4.0.8+**.
Build the JAR using the instructions below and place it in the Minecraft `mods` folder.
For a dedicated server, install the JAR on the server.

## Configuration

After the first launch, edit `config/rarer_spawn_islands-common.toml`. Restart Minecraft or the server to apply changes.

The tropical multiplier defaults to `0.1` (10% of the usual placement candidates). The volcanic multiplier defaults to `0.02` (2%). Both values accept numbers from `0.0` to `1.0`. Set a value to `1.0` for the original frequency or `0.0` to disable that island type.

## Development

Build with a Java 21 JDK using `./gradlew build` on Linux/macOS or `.\gradlew.bat build` on Windows.
The installable JAR is written to `build/libs/`. GitHub Actions builds the mod and checks the repository.

See [DEVELOPMENT.md](DEVELOPMENT.md) and [CONTRIBUTING.md](CONTRIBUTING.md).

## License

MIT. See [LICENSE](LICENSE) and [LEGAL.md](LEGAL.md).
