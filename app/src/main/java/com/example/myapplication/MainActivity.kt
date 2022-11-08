package com.example.myapplication

import android.app.PictureInPictureUiState
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, TestFragment())
            }
            Log.e("check", "actvitiy no bundle")
        } else {
            Log.e("check", "actvitiy has bundle")

        }
        Log.e("check", "Actvitiy onCreate")


    }

    override fun onStart() {
        super.onStart()
        Log.e("check", "Actvitiy start")

    }

    override fun onRestart() {
        super.onRestart()
        Log.e("check", "Actvitiy restart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("check", "Actvitiy onresume")

    }

    override fun onPause() {
        super.onPause()
        Log.e("check", "Actvitiy onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e("check", "Actvitiy onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("check", "Actvitiy onDestroy")

    }

}