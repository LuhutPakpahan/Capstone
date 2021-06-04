package com.b21cap0398.acnescan.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0398.acnescan.data.source.local.entity.Article
import com.b21cap0398.acnescan.databinding.ItemDailyReadBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DailyReadAdapter : RecyclerView.Adapter<DailyReadAdapter.CustomViewHolder>() {

    private val dailyReadList = ArrayList<Article>()

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    fun setDailyReadList(list: List<Article>) {
        dailyReadList.clear()
        dailyReadList.addAll(list)
    }

    inner class CustomViewHolder(val binding: ItemDailyReadBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(article: Article) {
            binding.apply {
                tvTitleArticle.text = article.title
                tvDescriptionArticle.text = article.description
                tvWrittenDate.text = "Date: ${article.date}"

                Glide.with(itemView.context)
                    .load(article.image_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivArticleImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ItemDailyReadBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(dailyReadList[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(dailyReadList[position])
        }
    }

    override fun getItemCount(): Int {
        return dailyReadList.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }
}