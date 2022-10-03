package com.example.myapplication

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class TestPagingProvider @Inject constructor() {

    fun getPager() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        initialKey = 0
    ) {
        getPagingSource()
    }

    private fun getPagingSource() = object : PagingSource<Int, String>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
            val key = params.key ?: 0

            val data = mutableListOf<String>().apply {
                var value = key * 10
                for (i in 0..9) {
                    add((value++).toString())
                }
            }

            Log.e("check", "data Load key: $key ${Thread.currentThread()}")

            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = key + 1
            )
        }

        override fun getRefreshKey(state: PagingState<Int, String>): Int? {
            return null
        }
    }
}