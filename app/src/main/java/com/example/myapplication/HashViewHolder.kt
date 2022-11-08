package com.example.myapplication

import android.content.Context
import android.util.Log
import android.webkit.WebView
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HashViewHolder @Inject constructor (
    @ApplicationContext context: Context
        ) {


    val hash = mutableListOf<TestViewHolder>()


}
