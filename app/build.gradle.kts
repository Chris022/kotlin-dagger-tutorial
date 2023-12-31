plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.0"

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Apply the ksp plugin
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use the JUnit 5 integration.
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:32.1.1-jre")

    //Dagger dependencies
    implementation ("com.google.dagger:dagger:2.47")
    annotationProcessor ("com.google.dagger:dagger-compiler:2.47")
    ksp("com.google.dagger:dagger-compiler:2.47")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("chris022.dagger.tutorial.AppKt")
}

//This is requred so that gradle forwards the System.in to the program
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}