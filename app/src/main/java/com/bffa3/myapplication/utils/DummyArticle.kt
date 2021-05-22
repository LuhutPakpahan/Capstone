package com.bffa3.myapplication.utils

import com.bffa3.myapplication.data.entity.Article

object DummyArticle {
    fun addDummyArticle() : List<Article> {
        val list = ArrayList<Article>()
        list.add(Article("1", "1", "1"))
        list.add(Article("2", "2", "2"))
        list.add(Article("3", "3", "3"))
        list.add(Article("4", "4", "4"))
        return list
    }
}