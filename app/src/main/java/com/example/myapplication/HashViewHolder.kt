package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.MutableContextWrapper
import android.view.LayoutInflater
import android.webkit.WebView
import com.example.myapplication.databinding.ItemWebViewholderBinding

@SuppressLint("StaticFieldLeak")
object HashViewHolder {
    val webHash = mutableListOf<TestWebViewHolder>()

    val defaultHash = mutableListOf<TestViewHolder>()

    var testViewHolder: WebView? = null
}