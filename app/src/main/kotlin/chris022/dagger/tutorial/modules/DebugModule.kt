package chris022.dagger.tutorial.modules

import chris022.dagger.tutorial.interfaces.ILogger
import chris022.dagger.tutorial.ConsoleLogger

import dagger.Binds
import dagger.Module

@Module
abstract class DebugModule{
    @Binds
    abstract fun logger(logger: ConsoleLogger): ILogger;
}