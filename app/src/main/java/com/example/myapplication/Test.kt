package com.example.myapplication

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun main() {
    println(What.valueOf("WOEO"))
    val a = What.GOOD
}


enum class What() {
    GOOD,
    WOEO
}