package com.demate.appfundamentals

fun main() {

    val input = Result.ERROR
    getResult(result = Repository.getCurrentState())
    Repository.finishFetch()
    getResult(result = Repository.getCurrentState())
    Repository.error()
    getResult(result = Repository.getCurrentState())

    Repository.anotherCustomeFailure()
    getResult(result = Repository.getCurrentState())
    Repository.customFailure()

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

    fun customFailure() {
        loadState = Failure.AnotherCustomFailure(NullPointerException("Null pointer exception"))
    }

    fun anotherCustomeFailure() {
        loadState = Failure.CustomerFailure(Exception("Customer failure"))
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

        is Failure.AnotherCustomFailure -> {
            println("Another Custom Failure")
            println("Exception: ${result.exception}")
        }
        is Failure.CustomerFailure -> {
            println("Customer Failure")
            println("Exception: ${result.exception}")
        }

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

sealed class Result

data class Success(val dataFetched: String?) : Result()
data class Error(val exception: Exception) : Result()
object NotLoading: Result()
object Loading: Result()


sealed class Failure: Result() {
    data class CustomerFailure(val exception: Exception) : Failure()
    data class AnotherCustomFailure(val exception: NullPointerException) : Failure()


}