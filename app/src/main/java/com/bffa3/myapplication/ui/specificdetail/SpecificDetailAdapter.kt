package com.bffa3.myapplication.ui.specificdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bffa3.myapplication.data.entity.AcnePicture
import com.bffa3.myapplication.databinding.ItemSpecificDetailBinding

class SpecificDetailAdapter() : RecyclerView.Adapter<SpecificDetailAdapter.CustomViewHolder>() {

    private val listPhoto = ArrayList<AcnePicture>()

    fun setListPhoto(listPhoto: List<AcnePicture>) {
        this.listPhoto.clear()
        this.listPhoto.addAll(listPhoto)
    }

    inner class CustomViewHolder(val binding: ItemSpecificDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(acnePicture: AcnePicture) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ItemSpecificDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(listPhoto[position])
    }

    override fun getItemCount() = listPhoto.size
}