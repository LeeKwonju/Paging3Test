package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.view.doOnAttach
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.myapplication.databinding.ItemViewholderBinding
import javax.inject.Inject


class TestAdapter(): ListAdapter<Int, RecyclerView.ViewHolder>(DiffUtilHelper()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 ->         ItemViewHolder(
                ItemViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )
            else   ->   SecondViewHolder(
                ItemViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))

        }



    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("check", "item created, ${getItem(position)}")

        (holder as? ItemViewHolder)?.bind(getItem(position) )


        (holder as? SecondViewHolder)?.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) < 1000) 0 else 1
     }



    private class DiffUtilHelper: DiffUtil.ItemCallback<Int>() {

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return true
        }
    }
}


class ItemViewHolder(val binding: ItemViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: Int) {

        binding.data = text.toString() + "It's One"

    }
}

class SecondViewHolder(val binding: ItemViewholderBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(text: Int) {
        binding.data = text.toString() + "It's Two"

    }
}