package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.net.URI


@AndroidEntryPoint
class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding

    val viewModel: TestViewModel by viewModels()

    val adapter by lazy {
        TestAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondBinding.inflate(inflater)

        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

//            binding.testImageView.setImageURI(it.data?.data)
//            val result = requireActivity().applicationContext.contentResolver.openFileDescriptor(it.data!!.data!!, "r").use {
//                FileInputStream(it!!.fileDescriptor).use {
//                    it.readBytes()
//                }
//            }
//            Log.e("check", "$result")
            Log.e("check", "$it")
        }

        WebView(requireContext()).loadUrl("")
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        Uri.parse("tel:4441234").let {
            val intent2 = Intent(Intent.ACTION_DIAL, it)
            activityResultLauncher.launch(intent2)

        }




    }

    override fun onStop() {
        super.onStop()
        Log.e("check", "second  Fragment onstop")

    }

    override fun onPause() {
        super.onPause()
        Log.e("check", "second  Fragment onpause")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("check", "second  Fragment destroyview")

    }
}