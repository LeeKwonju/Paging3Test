package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class TestFragment: Fragment() {

    lateinit var binding: FragmentTestBinding

    val adapter by lazy {
        TestAdapter()
    }

    private val viewModel: TestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTestBinding.inflate(
            inflater,
            container,
            false
        ).also {
            binding = it
            binding.recyclerview.adapter = adapter
            binding.button.setOnClickListener {
                viewModel.getPagingData()
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    HashViewHolder.hash.clear()
                    Log.e("check", "Thread : ${Thread.currentThread()}")
                    viewModel.getPagingData().collectLatest {
                        withContext(Dispatchers.Main) {
                            adapter.submitData(it)
                        }
                    }
                }
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}