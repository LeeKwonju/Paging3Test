package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

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
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("requestKey")
            Toast.makeText(requireActivity(), "$result, resultReceive", Toast.LENGTH_SHORT).show()

        }

        return FragmentTestBinding.inflate(
            inflater,
            container,
            false
        ).also {
            binding = it
            binding.button.setOnClickListener {
                parentFragmentManager.commit {

                    replace(R.id.fragmentContainer, SecondFragment())
                    addToBackStack("")

                }
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}