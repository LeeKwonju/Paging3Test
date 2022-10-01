package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemViewholderBinding
import com.example.myapplication.databinding.ItemWebViewholderBinding

class TestWebViewHolder(private val binding: ItemWebViewholderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: String) {
        binding.webView.loadUrl("https://www.google.com")
        binding.container.layoutParams.apply {
            this.height = 200
            binding.executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): TestWebViewHolder {
            return TestWebViewHolder(
                ItemWebViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

}