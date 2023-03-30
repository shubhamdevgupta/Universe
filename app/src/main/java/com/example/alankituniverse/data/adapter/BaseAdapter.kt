package com.example.alankituniverse.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, VB : ViewDataBinding>(private val layoutId: Int) :
    RecyclerView.Adapter<BaseAdapter.Companion.BaseViewHolder<VB>>() {

    var items = mutableListOf<T>()
    fun addItems(items: List<T>) {
        this.items.clear()
        this.items = items as MutableList<T>
        notifyDataSetChanged()
    }

    var context: Context? = null

    override fun getItemCount() = items.size

    var listener: ((view: View, item: T, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<VB>(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    )

    companion object {
        class BaseViewHolder<VB : ViewDataBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root)
    }
}