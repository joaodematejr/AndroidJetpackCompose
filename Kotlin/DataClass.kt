fun main() {
    /** var - mutable ... */

    val person = Person("John", 25)

    println(person)

    val listOfPerson = listOfPerson(person, Person("Alice", 25), Person("Bob", 25))

    listOfPerson.forEach { println(it) }
    
}


data class Person(val name: String, val age: Int)
