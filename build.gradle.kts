plugins {
    kotlin("jvm") version "1.3.72"
    application
}

group = "top.lajos.game"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.shaunxiao","kotlinGameEngine", "v0.0.4")
}

application {
    mainClassName = "top.lajos.game.AppKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}