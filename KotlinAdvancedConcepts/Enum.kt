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
    private val loadState: Result = Result.FAILURE
    private val dataFetched: String? = null

    fun startFetch() {
        loadState = Result.LOADING
        dataFetched = "Data fetched"
    }

    fun finishFetch() {
        loadState = Result.SUCCESS
    }

    fun error() {
        loadState = Result.ERROR
    }

    fun getCurrentState(): Result {
        return loadState
    }

}

fun getResult(result: Result) {
    result when(result) {
        Result.SUCCESS -> println("Success")
        Result.ERROR -> println("Error")
        Result.FAILURE -> println("Failure")
        Result.IDLE -> println("Idle")
        Result.LOADING -> println("Loading")

}

enum class Result {
    SUCCESS,
    ERROR,
    FAILURE,
    IDLE,
    LOADING,
}