plugins {
	id("fabric-loom") version "1.6.1"
	id("maven-publish")
}

version = "1.0.0"
group = "com.yourname.modnamehere"

repositories {
	mavenCentral()
	maven { url = uri("https://api.modrinth.com/maven") }
	maven { url = uri("https://maven.fabricmc.net/") }
}

dependencies {
	minecraft("com.mojang:minecraft:1.21")
	mappings(loom.officialMojangMappings())
	modImplementation("net.fabricmc:fabric-loader:0.15.11")
	modImplementation("net.fabricmc.fabric-api:fabric-api:0.97.3+1.21")
}

tasks.withType<JavaCompile> {
	options.release.set(21)
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}