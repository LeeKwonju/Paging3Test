package com.example.myapplication

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestAdapter : PagingDataAdapter<String, RecyclerView.ViewHolder>(DiffUtilHelper()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val a = System.currentTimeMillis()
        var result: RecyclerView.ViewHolder? = null
        if (viewType == 0) {
            result = TestWebViewHolder.from(parent)
            HashViewHolder.webHash.add(result)
            Log.e(
                "check",
                "WebviewHolderCreated total ViewHolder : ${HashViewHolder.webHash.size}, it take ${System.currentTimeMillis() - a}"
            )

        } else {
            result = TestViewHolder.from(parent)
            HashViewHolder.defaultHash.add(result)
            Log.e(
                "check",
                "DefaultviewHolderCreated total ViewHolder : ${HashViewHolder.defaultHash.size}, it take ${System.currentTimeMillis() - a}"
            )
        }
        return result!!
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            Log.e("check", "viewholder binded index :  ${HashViewHolder.webHash.indexOf(holder)}")

            (holder as TestWebViewHolder).bind(getItem(position) ?: "")
            holder.itemView.layoutParams.apply {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    height = 700
                }
            }
        } else {
            Log.e("check", "viewholder binded index :  ${HashViewHolder.defaultHash.indexOf(holder)}")

            (holder as TestViewHolder).bind(getItem(position) ?: "")
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (position != 0) 0 else 1
    }


    private class DiffUtilHelper : DiffUtil.ItemCallback<String>() {

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }
    }
}