# Kotlin Dagger Tutorial
My attempt at creating a Dagger tutorial (for Kotlin).
## Intro
The goal of this tutorial is to teach the absolute basics needed to create a simple application. More in depth examples and documentation can be found on the [Dagger Website](https://dagger.dev/dev-guide/).
## Installation
For a more general installation guide check the [Dagger Readme](https://github.com/google/dagger). In this document I will be using Gradle as buildsystem.

To get started create a new gradle kotlin project.
```bash
gradle init
```
Next open `app/build.gradle.kts` and add the **ksp** plugin by adding the following line to the "plugins" section:
```kotlin
// Apply the ksp plugin
id("com.google.devtools.ksp") version "1.9.0-1.0.12"
```

Next add the following lines to the "dependencies" section:
```kotlin
//Dagger dependencies
implementation ("com.google.dagger:dagger:2.47")
annotationProcessor ("com.google.dagger:dagger-compiler:2.47")
ksp("com.google.dagger:dagger-compiler:2.47")
```

Then at to bottom of the file add the following lines:
```kotlin
//This is requred so that gradle forwards the System.in to the program
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
```

When you now run the programm everything should compile and run just fine!
```bash
gradle run
```