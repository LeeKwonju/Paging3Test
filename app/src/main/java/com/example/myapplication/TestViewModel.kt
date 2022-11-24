package com.example.myapplication

import android.net.Uri
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class TestViewModel @Inject constructor(
) : ViewModel() {

    var a = 8

    var uri: Uri? = null

    val ff = MutableSharedFlow<Int>()

    var testList = mutableListOf<Int>().apply {
        for (i in 1..100) {
            add(i)
        }
    }

    fun settUri(uri: Uri) {
        this.uri = uri
    }

    fun testChange() {
        viewModelScope.launch {
            delay(500)
            a= 10
        }
    }


    fun changeTestList(index: Int): List<Int> {
        val index2 = testList.indexOfFirst {
            it > 1000
        }
        testList = testList.filter {
            it < 1000
        }.toMutableList()
        val target =  if (index2 < index && index2 != -1) {
            index - 1} else {index}
        testList.add(target, 10000)
        return testList
    }



    fun getList() = testList.toList()

    fun changeList() = testList.apply {
        testList.add(1, 10000)    }.toList()


}



