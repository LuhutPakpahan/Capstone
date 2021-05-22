package com.bffa3.myapplication.ui.specificdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bffa3.myapplication.databinding.ItemSpecificDetailBinding

class SpecificDetailAdapter() : RecyclerView.Adapter<SpecificDetailAdapter.CustomViewHolder>() {

    private val listPhoto = ArrayList<Int>()

    fun setListPhoto(listPhoto: List<Int>) {
        this.listPhoto.clear()
        this.listPhoto.addAll(listPhoto)
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(val binding: ItemSpecificDetailBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ItemSpecificDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.ivItem.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return listPhoto.size
    }
}