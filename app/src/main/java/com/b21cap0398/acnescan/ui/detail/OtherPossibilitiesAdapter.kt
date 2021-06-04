package com.b21cap0398.acnescan.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.Possibility
import com.b21cap0398.acnescan.databinding.ItemOtherAcnePossibilityBinding
import java.text.DecimalFormat

class OtherPossibilitiesAdapter() :
    RecyclerView.Adapter<OtherPossibilitiesAdapter.CustomViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val otherPossibilities = ArrayList<Possibility>()

    fun setList(list: List<Possibility>) {
        otherPossibilities.clear()
        otherPossibilities.addAll(list)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CustomViewHolder(val binding: ItemOtherAcnePossibilityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Possibility) {
            binding.tvNameOfAcne.text = data.acne_name[0].toUpperCase() + data.acne_name.substring(1)
            binding.tvPercentageOfAcne.text = "Possibility: ${DecimalFormat("##.#").format(data.possibility)} %"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherPossibilitiesAdapter.CustomViewHolder {
        return CustomViewHolder(
            ItemOtherAcnePossibilityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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
        fun onItemClicked(data: Possibility)
    }
}