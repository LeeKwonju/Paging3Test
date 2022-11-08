package com.example.myapplication

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis



@Volatile
var a = 1

var _b: Int? = null
val b: Int
    get() = _b!!

fun main() {
    val ee = 2 shl 30
    println(2 shl 30)
    println(2 shl 30 shl 32)

}

