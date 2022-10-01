package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemViewholderBinding
import com.example.myapplication.databinding.ItemWebViewholderBinding

class TestWebViewHolder(private val binding: ItemWebViewholderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.webView.webViewClient = WebViewClient()
    }

    companion object {
        fun from(parent: ViewGroup): TestViewHolder {
            return TestViewHolder(
                ItemViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

}