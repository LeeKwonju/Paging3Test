package com.example.myapplication

import android.content.MutableContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.fragment.app.commit
import com.example.myapplication.databinding.ItemWebViewholderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            HashViewHolder.testViewHolder = WebView(MutableContextWrapper(applicationContext))
        }
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, TestFragment())
        }
    }
}