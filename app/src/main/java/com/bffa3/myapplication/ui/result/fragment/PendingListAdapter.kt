package com.bffa3.myapplication.ui.result.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bffa3.myapplication.data.entity.ResultAcne
import com.bffa3.myapplication.databinding.ItemResultBinding

class PendingListAdapter : RecyclerView.Adapter<PendingListAdapter.CustomViewHolder>() {

    private val resultAcneList = ArrayList<ResultAcne>()

    fun setList(list: List<ResultAcne>) {
        resultAcneList.clear()
        resultAcneList.addAll(list)
    }

    inner class CustomViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(resultAcne: ResultAcne) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(resultAcneList[position])
    }

    override fun getItemCount(): Int {
        return resultAcneList.size
    }
}