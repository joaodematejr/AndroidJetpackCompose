fun main() {
    /** var - mutable ... */
    println("Hello world!".append(" Welcome to Kotlin"))
    println("Hello world!".removeFirstAndLastChars())
   
}

fun String.append(toAppend: String): String = return this.plus(toAppend)

fun String.removeFirstAndLastChars(): String = return this.substring(1, this.length - 1)