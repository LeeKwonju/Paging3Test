package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemViewholderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestViewHolder(
    private val binding: ItemViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.data = text
    }



    companion object {
        fun from(parent: ViewGroup): TestViewHolder {
            return TestViewHolder(
                ItemViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).apply {
                    this.clickListener = getClickListener(this)
                }
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