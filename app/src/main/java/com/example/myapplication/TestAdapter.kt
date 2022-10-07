package com.example.myapplication

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class TestAdapter: PagingDataAdapter<String, TestViewHolder>(DiffUtilHelper()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val a = System.currentTimeMillis()

        val result = TestViewHolder.from(parent)
        HashViewHolder.hash.add(result)
        return result
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(getItem(position) ?: "")
    }



    private class DiffUtilHelper: DiffUtil.ItemCallback<String>() {

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }
    }
}