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

## Implementation
This section deals with the implementation of the before described components.

### Factories
Factories are implmented simply as interfaces with a single function with the returntype of the object the factory creates.

```kotlin
interface CoffeeShop{
    fun coffee(): Coffee
}
```

Using the @Component annotation Dagger than automatically creates the implementation of the Factory.

**Info**: If your Object has Interfaces as Dependencies you need to provide Modules to the @Component annotation telling Dagger how to construct an instance of that Interface!

```kotlin
import dagger.Component

@Component([DebugModule::class])
interface CoffeeShop{
    fun coffee(): Coffee
}
```

### Dependencies
Whenever you want to create an object that should be created by Dagger you need to use the @Inject annotation. In our example the "Coffee" Object should be created by dagger (via the Factory).

```kotlin
import javax.inject.Inject

class Coffee @Inject constructor(){
    ...
    ...
    ...
}
```

@Inject annotated objects can also accept other dependencies. These will automatically be created by dagger and injected when required.

```kotlin
import javax.inject.Inject

class Coffee @Inject constructor(logger: ILogger){
    private val _logger = logger
    ...
    ...
}
```

When using @Inject without any additional annotations dagger will create a new instance of an object everytime it is required as an dependencie. This behaviour can be changed by using scopes. The most important scope is @Singleton. An object annotated with the @Singleton annotation will only be created once and the same instace will than be supplied everytime it is required.

```kotlin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConsoleLogger @Inject constructor(){
    ...
    ...
}
```

### Modules
A Module is simply an abstract class annotated with the @Module annotation. For every dependency it resolves it than has a abstract function accepting an instance of the Interface and returing the interface. This function needs to be annotated with the @Binds annotation

```kotlin
import dagger.Binds
import dagger.Module

@Module
abstract class DebugModule{
    @Binds
    fun logger(logger: ConsoleLogger):ILogger
}
```

For very simple objects it is also possbile to implement the instance of the interface right in the Module. (In this case the ILogger needs to be implmented as a Kotlin SAM interface)

```kotlin
import dagger.Provides
import dagger.Module

@Module
abstract class DebugModule{
    @Provides
    fun logger():ILogger {
        return ILogger { println(it)}
    }
}
```