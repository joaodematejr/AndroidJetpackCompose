package com.demate.jetweatherforecast.data

class DataOrException<T, Boolean, E : Exception>(
    val data: T? = null,
    var loading: Boolean? = null,
    val exception: E? = null
)