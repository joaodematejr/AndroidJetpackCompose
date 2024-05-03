package com.demate.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform