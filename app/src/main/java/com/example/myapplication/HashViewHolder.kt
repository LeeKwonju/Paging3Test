package com.example.myapplication

import android.app.Application
import android.content.MutableContextWrapper
import android.view.LayoutInflater
import com.example.myapplication.databinding.ItemWebViewholderBinding

object HashViewHolder {
    val webHash = mutableListOf<TestWebViewHolder>()

    val defaultHash = mutableListOf<TestViewHolder>()

}