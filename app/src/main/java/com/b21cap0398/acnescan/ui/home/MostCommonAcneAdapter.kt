package com.b21cap0398.acnescan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.CommonAcne
import com.b21cap0398.acnescan.databinding.ItemCommonAcnesBinding

class MostCommonAcneAdapter : RecyclerView.Adapter<MostCommonAcneAdapter.CustomViewHolder>() {

    private val listCommonAcnes = ArrayList<CommonAcne>()

    fun setListCommonAcnes(list: List<CommonAcne>){
        listCommonAcnes.clear()
        listCommonAcnes.addAll(list)
    }

    inner class CustomViewHolder(val binding: ItemCommonAcnesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(acne: CommonAcne) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ItemCommonAcnesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return listCommonAcnes.size
    }
}