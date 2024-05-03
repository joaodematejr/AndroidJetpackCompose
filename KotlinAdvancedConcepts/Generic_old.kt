package com.demate.appfundamentals

fun main() {

    val listOfItems = listOf("apple", "banana", "orange", "grapes", "mango")
    val listOfNumber = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val finder = Finder(listOfNumber)
    finder.findItem(element = "apple") {
        println("Item found: $it")
    }
        
}

class Finder<T>(private val list: List<T>) {
    fun findItem(element: T, foundItem:(element: T?) -> Unit) {
        val itemFoundList = list.filter {
            it == element
        }

        if (itemFoundList.isNotEmpty()) {
            foundItem(itemFoundList.first())
        } else {
            foundItem(null)
        }
    }
}
