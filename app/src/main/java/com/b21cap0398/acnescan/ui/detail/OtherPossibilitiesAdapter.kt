package com.b21cap0398.acnescan.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.OtherPossibility
import com.b21cap0398.acnescan.databinding.ItemOtherAcnePossibilityBinding

class OtherPossibilitiesAdapter : RecyclerView.Adapter<OtherPossibilitiesAdapter.CustomViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val otherPossibilities = ArrayList<OtherPossibility>()

    fun setList(list: List<OtherPossibility>) {
        otherPossibilities.clear()
        otherPossibilities.addAll(list)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CustomViewHolder(val binding: ItemOtherAcnePossibilityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OtherPossibility) {
            binding.tvNameOfAcne.text = data.titleAcne
            binding.tvPercentageOfAcne.text = data.percentage
            binding.tvDescOfAcne.text = data.description
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherPossibilitiesAdapter.CustomViewHolder {
        return CustomViewHolder(ItemOtherAcnePossibilityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: OtherPossibilitiesAdapter.CustomViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(otherPossibilities[position]) }
        holder.bind(otherPossibilities[position])
    }

    override fun getItemCount(): Int {
        return otherPossibilities.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: OtherPossibility)
    }
}