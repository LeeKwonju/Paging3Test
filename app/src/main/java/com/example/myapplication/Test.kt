package com.example.myapplication

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun main() {
    val a = flow<Int> {
        println("thread = ${Thread.currentThread()}")
        emit(1)
    }
    CoroutineScope(Dispatchers.Default).launch {
        a.collectLatest {
            println("$it and thread = ${Thread.currentThread()}")
        }
    }

    println("Out " +
            "+thread = ${Thread.currentThread()}")

    Thread.sleep(2000)
}