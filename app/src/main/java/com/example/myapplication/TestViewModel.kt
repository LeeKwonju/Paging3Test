package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class TestViewModel @Inject constructor(private val testPagingProvider: TestPagingProvider) : ViewModel() {

    fun getPagingData(): Flow<PagingData<String>> {
        return testPagingProvider.getPager().flow.flowOn(Dispatchers.Default)
    }
}



