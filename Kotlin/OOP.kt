fun main() {
   /** var - mutable ... */
    val car = Car(color = "Green", model = "2015")
    val secondCar = Car(color = "Purple", model = "2020")

    car.color = "Green"
    car.model = "2015"
    
    println("Car color: ${car.color}" + " model: ${car.model}")
    println("Second car color: ${secondCar.color}" + " model: ${secondCar.model}")

    car.speed(0, 200)
    car.drive()

    val name: String = "John"

    //truck
    val truck = Truck("Red", "2018")
    truck.drive()
    truck.speed(minSpeed = 20, maxSpeed = 90)



}

class Truck(color: String, model: String): Car(color, model) {
    override fun speed(minSpeed: Int, maxSpeed: Int) {
        val fullSpeed = minSpeed * maxSpeed
        println("Truck speed is $fullSpeed")

    }

    override fun drive() {
        println("Drive speed is 50")
    }


    fun load() {
        println("Truck is loading")
    }

    fun unload() {
        println("Truck is unloading")
    }
}

open class Car(var color: String = "Black", var model: String = "2019") {


    init {
        if (color == "") {
            color = "Black"
        } else {
            color = color
        }
        model = "2015"
    }

    //val color: String = color
    //val model: String = model

    open fun speed(minSpeed: Int, maxSpeed: Int) {
        println("Car speed is between $minSpeed and $maxSpeed")
    }

    fun stop() {
        println("Car is stopping")
    }

    open fun drive() {
        println("Car is driving")
    }

    fun start() {
        println("Car is starting")
    }

}