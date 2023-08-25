package chris022.dagger.tutorial

import javax.inject.Inject

import chris022.dagger.tutorial.interfaces.ILogger


class Coffee @Inject constructor(logger: ILogger){
    private val _logger = logger
    
    fun drink(){
        _logger.log("Drank coffee")
    }
}