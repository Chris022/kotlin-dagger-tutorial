package chris022.dagger.tutorial

import javax.inject.Inject
import javax.inject.Singleton

import chris022.dagger.tutorial.interfaces.ILogger

@Singleton
class ConsoleLogger @Inject constructor() : ILogger{
    override fun log(msg: String){
        println(msg)
    }
}