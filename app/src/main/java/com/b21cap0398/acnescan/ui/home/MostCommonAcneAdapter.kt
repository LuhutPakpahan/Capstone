package com.b21cap0398.acnescan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.CommonAcne
import com.b21cap0398.acnescan.databinding.ItemCommonAcnesBinding

class MostCommonAcneAdapter : RecyclerView.Adapter<MostCommonAcneAdapter.CustomViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val listCommonAcnes = ArrayList<CommonAcne>()

    fun setListCommonAcnes(list: List<CommonAcne>){
        listCommonAcnes.clear()
        listCommonAcnes.addAll(list)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
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
            onItemClickCallback.onItemClicked(listCommonAcnes[position])
        }
    }

    override fun getItemCount(): Int {
        return listCommonAcnes.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: CommonAcne)
    }
}