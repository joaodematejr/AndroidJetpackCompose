package com.demate.appfundamentals

fun main() {
    var world = "World"
    println("Hello, $world!")
    println("Hello, ${world}!")
    println("Hello, ${world.toUpperCase()}!")
    println("Hello, ${world.replace("World", "Kotlin")}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase()}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed()}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed().replace("O", "o")}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed().replace("O", "o").replace("L", "l")}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed().replace("O", "o").replace("L", "l").replace("T", "t")}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed().replace("O", "o").replace("L", "l").replace("T", "t").replace("I", "i")}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed().replace("O", "o").replace("L", "l").replace("T", "t").replace("I", "i").replace("N", "n")}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed().replace("O", "o").replace("L", "l").replace("T", "t").replace("I", "i").replace("N", "n").replace("K", "k")}!")
    println("Hello, ${world.replace("World", "Kotlin").toUpperCase().reversed().replace("O", "o").replace("L", "l").replace("T", "t").replace("I", "i").replace("N", "n").replace("K", "k").replace("T", "t")}!")

    
    var world2 = "World"
    world = world2
    println("Hello, $world2!")

    val world3 = "World

    //var mutable
    //val immutable


    //initialize immutable variables 
    val name: String
    name = "Kotlin"
    println("Hello, $name!")

    val name2: String
    val age: Int
    name2 = "Kotlin"
    age = 10
    println("Hello, $name2! You are $age years old!")

    //types in kotlin and their sizes and size mb
    //Byte 8 bits 1 byte
    //Short 16 bits 2 bytes
    //Int 32 bits 4 bytes
    //Long 64 bits 8 bytes
    //Float 32 bits 4 bytes
    //Double 64 bits 8 bytes
    //Char 16 bits 2 bytes
    //Boolean 8 bits 1 byte

    val myByte: Byte = 127
    val myShort: Short = 32760
    val myInt: Int = 2147483640
    val myLong: Long = 9223372036854775800
    val myFloat: Float = 3.14f
    val myDouble: Double = 3.141592653589793238
    val myChar: Char = 'A'
    val myBoolean: Boolean = true

    println("Byte: $myByte")
    println("Short: $myShort")
    println("Int: $myInt")
    println("Long: $myLong")
    println("Float: $myFloat")
    println("Double: $myDouble")
    println("Char: $myChar")
    println("Boolean: $myBoolean")

    //type conversion
    val myInt2: Int = 10
    val myLong2: Long = myInt2.toLong()
    val myFloat2: Float = myInt2.toFloat()
    val myDouble2: Double = myInt2.toDouble()
    val myChar2: Char = myInt2.toChar()
    val myBoolean2: Boolean = myInt2 > 5

    println("Int: $myInt2")
    println("Long: $myLong2")
    println("Float: $myFloat2")
    println("Double: $myDouble2")
    println("Char: $myChar2")
    println("Boolean: $myBoolean2")

    //operators
    val a = 10
    val b = 20
    val c = 30
    val d = 40
    val e = 50

    println("a + b = ${a + b}")
    println("a - b = ${a - b}")
    println("a * b = ${a * b}")
    println("a / b = ${a / b}")
    println("a % b = ${a % b}")

    println("a++ = ${a++}")
    println("++a = ${++a}")
    println("a-- = ${a--}")
    println("--a = ${--a}")

    println("a > b = ${a > b}")
    println("a < b = ${a < b}")
    println("a >= b = ${a >= b}")
    println("a <= b = ${a <= b}")
    println("a == b = ${a == b}")
    println("a != b = ${a != b}")

    println("a && b = ${a > b && a < c}")
    println("a || b = ${a > b || a < c}")
    println("!a = ${!a}")

    println("a and b = ${a and b}")
    println("a or b = ${a or b}")
    println("a xor b = ${a xor b}")
    println("a shl b = ${a shl b}")
    println("a shr b = ${a shr b}")
    println("a ushr b = ${a ushr b}")
    println("a.inv() = ${a.inv()}")

    /** var - mudatble ...*/

    val number = 1
    val oneLong = 1L


    //float and Double

    val myFloat3: Float = 3.14f
    val myDouble3: Double = 3.141592653589793238

    println("Float: $myFloat3")
    println("Double: $myDouble3")

    /** var - mutable ...*/

    val pi = 3.14f
    println("Pi: $pi")

    val e = 2.7182818284
    println("E: $e")
    val eFloat = 2.7182818284f
    println("E: $eFloat")

    //kotlin operators
    
    val a2 = 10
    val b2 = 20
    val c2 = 30
    val d2 = 40

    println("a2 + b2 = ${a2 + b2}")
    println("a2 - b2 = ${a2 - b2}")
    println("a2 * b2 = ${a2 * b2}")
    println("a2 / b2 = ${a2 / b2}")
    println("a2 % b2 = ${a2 % b2}")


    

}
