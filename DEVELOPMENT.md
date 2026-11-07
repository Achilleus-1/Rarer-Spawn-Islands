# Development

Install JDK 21. Run `./gradlew build` or `.\gradlew.bat build` from the repository root.
The wrapper pins Gradle. Minecraft, NeoForge, and mod metadata are configured in `gradle.properties`.

Run `python tools/check_repository.py` with Python 3.12 before submitting changes.
To check behavior in game, install the built JAR alongside Spawn 4.0.8+ and its required dependencies on Minecraft 1.21.1.
