plugins {
  kotlin("jvm") version "2.1.0"
}

allprojects {
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")

  dependencies {
    if (project.name != "kotlinCommon") implementation(project(":common:kotlinCommon"))
    implementation("com.github.ajalt.mordant", "mordant", "3.0.1")
  }
}