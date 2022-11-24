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

    var imageViewSize: Int = 50

    var imageSize = 720

    var isOriginal = false

    fun getImageURL(): String {
        return "$IMAGE_URL/$imageSize/none"
    }

    companion object {
        const val IMAGE_URL = "https://image.ohou.se/image/resize/bucketplace-v2-development/uploads-cards-snapshots-166765993018920208.jpeg/"
    }
}



