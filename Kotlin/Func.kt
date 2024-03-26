package com.demate.appfundamentals

fun main() {

    /*var - mutable */

    for (i in 1..3) {

        println("i: $i")
        
    }


    for (i in 1..1000) {
        if (i%3 == 0) println("i: $i")
    }

    calculate(1, 1000)
    calculate(1, 1000, 3)

}


fun calculate(first: Int, second: Int){
    for (i in first..second) {
        if (i%2 == 0) {
           println("$i is multiple of 2")
        }
    }
}

fun calculateM(first: Int, second: Int, multiple: Int){
    for (i in first..second) {
        if (i%multiple == 0) {
           println("$i is multiple of $multiple")
        }
    }
}


calculateMensagem(first: Int, second: Int, message = "is multiple of", multiple: Int)

fun calculateMensagem(first: Int = 300, second: Int = 20, message: String = "is multiple of", multiple: Int = 2){
    for (i in first..second) {
        if (i%multiple == 0) {
           println("$i $message $multiple")
        }
    }
}

val catAge = calculateCatAge(age = 12) * 10

if (checkAge(catAge)) {
    println("Cat is old $catAge")
} else {
    println("Cat is young $catAge")
}


fun calculateCatAge(age: Int): Int = age * 7


fun checkAge(catAge: Int): Boolean = catAge >= 14




