package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@HiltAndroidApp
class TestApplication: Application() {
}