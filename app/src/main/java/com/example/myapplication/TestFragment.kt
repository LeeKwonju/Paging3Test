package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.dataStore
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class TestFragment: Fragment() {

    lateinit var binding: FragmentTestBinding


    private val viewModel: TestViewModel by viewModels()

    private val adapter: TestAdapter by lazy {
        TestAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("requestKey")
            Toast.makeText(requireActivity(), "$result, resultReceive", Toast.LENGTH_SHORT).show()
        }

        return FragmentTestBinding.inflate(
            inflater,
            container,
            false
        ).also {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.saveData()

                viewModel.readData()
            }
            binding = it
            binding.button.setOnClickListener {
                Intent.CATEGORY_OPENABLE
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://010-2344-3434"))
                requireContext().startActivity(intent)
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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