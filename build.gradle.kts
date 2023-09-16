import org.gradle.kotlin.dsl.application

plugins {
    id ("application")
    id("java")
    id ("org.openjfx.javafxplugin") version "0.0.9"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

javafx {
    version = "17"
    modules = listOf( "javafx.controls", "javafx.fxml" )
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
        "base",
        "controls",
        "fxml",
        "swing",
        "graphics",
        "media"
)

val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    val javaFxVersion = 17
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
    implementation("mysql:mysql-connector-java:8.0.26")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("App")
}
