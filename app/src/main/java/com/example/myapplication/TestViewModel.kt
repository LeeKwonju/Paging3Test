package com.example.myapplication

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject




@HiltViewModel
class TestViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    val keyInt = intPreferencesKey("kk")
    val keyIntTwo = stringPreferencesKey("k")



    suspend fun saveData() {
        dataStore.edit { preferences ->
            preferences[keyInt] = 4
            preferences[keyIntTwo] = "wo"
        }
    }

    suspend fun readData() {
        dataStore.data.map { preferences ->
            preferences
        }.collect {
            Log.e("check", "${it}")
        }
    }

}



