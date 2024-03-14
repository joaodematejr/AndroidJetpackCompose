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

}


fun calculate(first: Int, second: Int){
    for (i in first..second) {
        if (i%2 == 0) {
           println("$i is multiple of 2")
        }
    }
}
