package com.demate.appfundamentals

fun main() {

    val input = Result.ERROR
    getResult(result = Repository.getCurrentState())
    Repository.finishFetch()
    getResult(result = Repository.getCurrentState())
    Repository.error()
    getResult(result = Repository.getCurrentState())

}

object Repository {
    private val loadState: Result = FAILURE
    private val dataFetched: String? = null

    fun startFetch() {
        loadState = LOADING
        dataFetched = "Data fetched"
    }

    fun finishFetch() {
        loadState = SUCCESS(dataFetched)
    }

    fun error() {
        loadState = ERROR(exception = Exception("Error fetching"))   
    }

    fun getCurrentState(): Result {
        return loadState
    }

}

fun getResult(result: Result) {
    result when(result) {
        is Error -> {
            println("Error")
            println("Exception: ${result.exception}")
        }
        is Success -> {
            println("Success")
            println("Data fetched: ${result.dataFetched}")
        }
        is Loading -> println("Loading")
        is NotLoading -> println("Not Loading")

        else -> println("Idle")


}
/* 
enum class Result {
    SUCCESS(val data: String),
    ERROR,
    IDLE,
    LOADING,
}
*/

abstract class Result

abstract class Success(val dataFetched: String?) : Result()
abstract class Error(val exception: Exception) : Result()
object NotLoading: Result()
object Loading: Result()