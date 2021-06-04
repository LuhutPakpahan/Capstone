package com.b21cap0398.acnescan.ui.result.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.databinding.ItemResultBinding
import com.b21cap0398.acnescan.utils.helper.FirebaseStorageEndpointHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class AcceptedListAdapter : RecyclerView.Adapter<AcceptedListAdapter.CustomViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val resultAcneList = ArrayList<AcneScanResult>()

    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(list: List<AcneScanResult>) {
        resultAcneList.clear()
        resultAcneList.addAll(list)
    }

    inner class CustomViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(resultAcne: AcneScanResult, position: Int) {
            binding.apply {
                tvScanResultNum.text = "Scan Result ${position + 1}"
                tvDate.text = "Date : ${resultAcne.date?.take(10)}"
                FirebaseStorageEndpointHelper.getDownloadUrlOfReference(resultAcne.image_path!!).addOnSuccessListener {
                    Log.d("photo", "Photo load successs")
                    Glide.with(itemView.context)
                        .load(it)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivAcneImage)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(resultAcneList[position], position)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(resultAcneList[position]) }
    }

    override fun getItemCount(): Int {
        return resultAcneList.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: AcneScanResult)
    }
}