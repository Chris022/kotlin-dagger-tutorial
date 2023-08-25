package chris022.dagger.tutorial

import dagger.Component
import javax.inject.Singleton

import chris022.dagger.tutorial.modules.DebugModule

@Singleton
@Component(modules=[DebugModule::class])
interface CoffeeShop{
    fun brew(): Coffee
}