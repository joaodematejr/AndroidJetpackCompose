package com.demate.appfundamentals

fun main() {

    val listOfItems = listOf("apple", "banana", "orange", "grapes", "mango")
    val listOfNumber = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val finder = Finder(listOfNumber)
    finder.findItem(element = "apple") {
        println("Item found: $it")
    }
        
}

class Finder(private val list: List<String>) {
    fun findItem(element: String, foundItem:(element: String?) -> Unit) {
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
