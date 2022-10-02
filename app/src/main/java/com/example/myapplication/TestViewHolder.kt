package com.example.myapplication

import android.app.Activity
import android.app.Application
import android.content.MutableContextWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemViewholderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestViewHolder(
    private val webView: WebView
) : RecyclerView.ViewHolder(webView.rootView) {

    fun bind(text: String) {
        webView.loadUrl("https://www.google.com")
    }



    companion object {
        fun from(parent: ViewGroup): TestViewHolder {
            val webView = HashViewHolder.testViewHolder!!
            (webView.context as MutableContextWrapper).baseContext = parent.context
            webView.apply {
                layoutParams = ViewGroup.LayoutParams(parent.layoutParams.width, 2000)
            }
            return TestViewHolder(
                webView
            )
        }

        private fun getClickListener(binding: ItemViewholderBinding) = object : OnTestClickListener {
            override fun onClick(data: String) {
                binding.data = "it.CLicked"
            }
        }
    }
}

interface OnTestClickListener {

    fun onClick(data: String)

}