
fun sum_old(a: Int, b: Int): Int {
    return a + b
}
// val lambda: Type = {parameterList -: codeBody}
val sum: (Int, Int) -> Int = {a, b -> a + b}

fun main() {
    println(sum(1, 2))
}

val catAge: (Int) -> Int = {age -> age * 7}

fun main() {
    println(catAge(2))
}




//----------------------------------------------------------------
//USING THE "IT" LAMBDA KEYWORD

val catAge: (Int) -> Int = {it * 7}

fun main() {
    println(showName("catAge"))
}

//Lambda Expressions that Return Unit - Void

val showName: (String) -> Unit = {name -> println("Your name is $name")}

fun showName(name: String) {
    println("Your name is $name")
}


//Trailing Lambda

fun enhancedMessage(message: String, funAsParameter:() -> Int) {
    println("Your message is: $message")
    println("Your function returns: ${funAsParameter()}")
}

enhancedMessage("Hello World", {
    add(12, 12)
})









