package com.demate.jettrivia.data

data class DataOrException<T, Boolean, E : Exception>(
    val data: T? = null,
    val isLoading: Boolean? = null,
    val exception: E? = null
)