package com.example.myapplication


import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream
import com.example.myapplication.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL


@AndroidEntryPoint
class SecondFragment: Fragment() {

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

        CoroutineScope(Dispatchers.IO).launch {
            val connection = URL("https://www.sample-videos.com/img/Sample-png-image-10mb.png").openConnection()
            val inputStream = connection.getInputStream()
            val wrappedStream = RecyclableBufferedInputStream(inputStream, LruArrayPool())
            wrappedStream.mark(700000000)
            val result = BitmapFactory.decodeStream(wrappedStream)
            val good = result
            wrappedStream.reset()
            val result2 = BitmapFactory.decodeStream(wrappedStream)
            val good2 = result2
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