package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.datastore.dataStore
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentTestBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import retrofit2.http.GET
import javax.inject.Inject


@AndroidEntryPoint
class TestFragment: Fragment() {

    lateinit var binding: FragmentTestBinding

    private val viewModel: TestViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("check", "TestFragment onCreate")


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("check", "Test Fragment createview")

        return FragmentTestBinding.inflate(
            inflater,
            container,
            false
        ).also {
            binding = it
            binding.button.setOnClickListener {
                parentFragmentManager.commit {
                    replace(R.id.fragmentContainer, SecondFragment())
                    addToBackStack("a")
                }
            }
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("check", "TestFragment onViewCreated")

        FirebaseCrashlytics.getInstance().setCustomKey("what", "waht")
        val bundle = Bundle().apply {
               putString("hi","hi")
                putString("by","by")
            }
        FirebaseAnalytics.getInstance(requireActivity().application).logEvent("hi", bundle)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("check", "TestFragment onAttach")

    }

    override fun onStart() {
        super.onStart()
        Log.e("check", "TestFragment Start")

    }

    override fun onResume() {
        super.onResume()
        Log.e("check", "TestFragment Resume")

    }

    override fun onDetach() {
        super.onDetach()
        Log.e("check", "TestFragment onDetach")

    }

    override fun onPause() {
        super.onPause()
        Log.e("check", "TestFragment onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e("check", "TestFragment onStop")

    }




    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("check", "TestFragment ViewDestroyed")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("check", "TestFragment Destroyed")
    }
}