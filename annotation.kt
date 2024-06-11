//https://developer.android.com/jetpack/androidx/releases/room?hl=en#kts

fun main() {
    val engine = Engine()
    val turbo = TurboEngine()
    val car = Car(engine, turbo)
    
    car.startCar()
    car.engine.start()
    car.turbo.start()
}

class Engine() {
    fun start() {
        println("Engine X started...")
    }
 
}
class TurboEngine() {
    fun start() {
        println("Start Turbo engine X")
    }
}
class Car(val engine: Engine, val turbo: TurboEngine) {
    //val engine = Engine() // not good
    fun startCar() {
        println("starting car... ${engine.start()}")
    }
}
