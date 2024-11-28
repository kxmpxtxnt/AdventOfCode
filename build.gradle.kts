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
    if (project.name != "commonKotlin") implementation(project(":common:commonKotlin"))
    implementation("com.github.ajalt.mordant", "mordant", "3.0.1")
  }
}