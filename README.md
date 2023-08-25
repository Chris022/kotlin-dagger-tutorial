# Kotlin Dagger Tutorial
My attempt at creating a Dagger tutorial (for Kotlin).
## Intro
The goal of this tutorial is to teach the absolute basics needed to create a simple application. More in depth examples and documentation can be found on the [Dagger Website](https://dagger.dev/dev-guide/). This tutorial assumes a basic understanding of dependency injection and is meant more as a dictionary than a real tutorial.
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
## Overview
This section descripes the different Components there are to a dagger application.

### Factories
A factory simply creates a new instance of an object. It takes care of supplying all required Dependencies to the created object.

### Dependencies
Objects that act as a dependencies and therefore can be supplied to other objects.

### Modules
Modules act as descriptions for Dagger and tell it how to create new dependencies. Modules are only needed if the type of the dependency is an interface. Lets for example say you want to inject an object of type "ILogger"(=Interface) unsing the implementation "ConsoleLogger". Then you simple create a Module describing this. Every factory needs to reference the modules it uses.
