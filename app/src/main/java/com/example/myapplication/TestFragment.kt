package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.os.bundleOf
import androidx.datastore.dataStore
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.example.myapplication.databinding.FragmentTestBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import retrofit2.http.GET
import java.io.File
import java.io.InputStream
import java.net.URL
import javax.inject.Inject


@AndroidEntryPoint
class TestFragment: Fragment() {

    lateinit var binding: FragmentTestBinding

    private val viewModel: TestViewModel by activityViewModels()

    var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTestBinding.inflate(
            inflater,
            container,
            false
        ).also { it ->
            binding = it
            binding.button1.setOnClickListener {
                SecondFragment().apply {
                    setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_NoTitleBar_Fullscreen)
                }.show(childFragmentManager, "a")
            }
            binding.button2.setOnClickListener {
                time = System.currentTimeMillis()
                if (!viewModel.isOriginal) {
                    loadNormal()
                } else {
                    Glide.with(requireContext())
                        .asBitmap()
                        .load(viewModel.getImageURL())
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(object: CustomTarget<Bitmap>() {
                            override fun onLoadCleared(placeholder: Drawable?) {
                            }

                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                binding.testImageView.setImageBitmap(resource)
                                binding.testImageView.scaleType = ImageView.ScaleType.CENTER_CROP
                                binding.networkSize.text = "Network consumed Size: ${resource.rowBytes}bytes"

                                binding.timeTextView.text = "Time : ${System.currentTimeMillis() - time}ms"
                                binding.sizeTextView.text = "bitmap Size: ${resource.rowBytes}bytes"
                            }
                        })
                }

                binding.testImageView.layoutParams.width = viewModel.imageViewSize.toDp()
                binding.testImageView.layoutParams.height = viewModel.imageViewSize.toDp()
            }

            binding.button3.setOnClickListener {
                binding.testImageView.setImageBitmap(null)
                CoroutineScope(Dispatchers.Default).launch {
                    Glide.get(requireContext()).clearDiskCache()
                    Log.e("cheeck", "clear ENDED")
                }
                Glide.get(requireContext()).clearMemory()
                parentFragmentManager.commit {
                    replace(R.id.fragmentContainer, TestFragment())
                }
            }
        }.root


    }

    private fun loadNormal() {
        Glide.with(binding.testImageView)
            .load(viewModel.getImageURL())
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .signature(ObjectKey(System.currentTimeMillis()))
                    .skipMemoryCache(true)
                    .let {
                        if (viewModel.isOriginal) {
                            it.override(SIZE_ORIGINAL)
                        } else {
                            it
                        }
                    }
                    .centerCrop()
            )
            .listener(
                object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        Glide.with(requireContext())
                            .asBitmap()
                            .load(model as String)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .signature(ObjectKey(System.currentTimeMillis()))
                            .skipMemoryCache(true)
                            .into(object : CustomTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                                override fun onLoadCleared(placeholder: Drawable?) {

                                }

                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: Transition<in Bitmap>?
                                ) {
                                    binding.networkSize.text = "Network consumed Size: ${resource.rowBytes}bytes"
                                }
                            })


                        binding.timeTextView.text = "Time : ${System.currentTimeMillis() - time}ms"
                        binding.sizeTextView.text = "bitmap Size: ${resource?.toBitmap()?.rowBytes}bytes"

                        return false
                    }
                }
            )
            .into(binding.testImageView)
    }
    private fun Int.toDp(): Int {
        val displayMetrics = requireContext().resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics).toInt()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }



}