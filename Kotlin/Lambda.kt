
fun sum_old(a: Int, b: Int): Int {
    return a + b
}
// val lambda: Type = {parameterList -: codeBody}
val sum: (Int, Int) -> Int = {a, b -> a + b}

fun main() {
    println(sum(1, 2))
}