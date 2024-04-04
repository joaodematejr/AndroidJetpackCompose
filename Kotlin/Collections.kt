fun main(){
    //Introduction to Collections
    val myList = listOf(1, 2, 3, 4, 5)
    val myListNames = listOf("hello", "world", "kotlin", "programming")

    val myMutableList = mutableListOf(1, 2, 3, 4, 5)

    myList.add(6)
    myList.removeAt(1)

    println("myList contains: ${myList.size}")

    println("myListNames contains: ${myListNames.size}")

    println("myList second element contains: ${myList[1]}")

    println("index of element in myList is ${myList.indexOf(3)}")

    for (name in myListNames) {
        println(name)
    }

    myListNames.forEach {
        println(it)
    }

    //kotlin sets and maps collections

    val mySet = setOf(1, 2, 3, 4, 5, 5, 5, 5, 5)
    val myMutableSet = mutableSetOf(1, 2, 3, 4, 5, 5, 5, 5, 5)
    myMutableList.add(34)
    myMutableSet.add(3)
    println(myMutableSet)

    val myMap = mapOf(1 to "one", 2 to "two", 3 to "three")
    val myMutableMap = mutableMapOf(1 to "one", 2 to "two", 3 to "three")
    myMutableMap[4] = "four"
    println(myMutableMap)

    if ("one" in myMutableMap) println("one is one")
    if (4 in myMutableMap.values) println("four is four")

    val myNewList = mutableListOf<String>()
    myNewList.add("one")
    myNewList.add("two")
    myNewList.add("three")

    for (i in 1..10) {
        myNewList.add(i, "hey" + i)
    }

    val myListInt = listOf<Int>(1, 2, 3, 4)

    val myNewListInt = mutableListOf<Int>()
    myNewListInt.add(1)

    for (i in 1..10) {
        myNewListInt.add(i)
    }

    val empty = emptyList<String>()
    val emptySet = emptySet<Int>()
    val emptyMap = emptyMap<Int, String>()


    //COLLECTION FILTERS

    val myListOfNames = listOf("a", "b", "c", "d", "e")

    val filteredList = myListOfNames.filter { it == "a" }
    //endsWith() is a function that checks if a string ends with a particular character
    //startWith() is a function that checks if a string start with a particular character
    val filteredList2 = myListOfNames.filter { it.endsWith("l", ignoreCase= true) && it.endsWith("e", ignoreCase= true)}

    println(filteredList)


 

}