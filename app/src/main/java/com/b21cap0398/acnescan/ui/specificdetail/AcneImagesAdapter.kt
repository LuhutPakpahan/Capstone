package com.b21cap0398.acnescan.ui.specificdetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.databinding.ItemAcnePhotoBinding

class AcneImagesAdapter() :
    RecyclerView.Adapter<AcneImagesAdapter.CustomViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val imagePaths = ArrayList<String>()

    fun setList(list: List<String>) {
        imagePaths.clear()
        imagePaths.addAll(list)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CustomViewHolder(val binding: ItemAcnePhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: String) {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AcneImagesAdapter.CustomViewHolder {
        return CustomViewHolder(
            ItemAcnePhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: AcneImagesAdapter.CustomViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int {
        return imagePaths.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }
}