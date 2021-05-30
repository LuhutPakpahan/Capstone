package com.b21cap0398.acnescan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.Article
import com.b21cap0398.acnescan.databinding.ItemDailyReadBinding

class DailyReadAdapter : RecyclerView.Adapter<DailyReadAdapter.CustomViewHolder>() {

    private val dailyReadList = ArrayList<Article>()

    fun setDailyReadList(list: List<Article>) {
        dailyReadList.clear()
        dailyReadList.addAll(list)
    }

    inner class CustomViewHolder(val binding: ItemDailyReadBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ItemDailyReadBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(dailyReadList[position])
    }

    override fun getItemCount(): Int {
        return dailyReadList.size
    }
}