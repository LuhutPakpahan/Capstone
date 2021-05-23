package com.bffa3.myapplication.utils

import com.bffa3.myapplication.data.entity.Article

object DummyArticle {
    fun addDummyArticle() : List<Article> {
        return listOf(
            Article(
                "1",
                "Jerawat 1",
                "Deskripsi Jerawat 1"),
            Article(
                "2",
                "Jerawat 2",
                "Deskripsi Jerawat 2"),
            Article(
                "3",
                "Jerawat 3",
                "Deskripsi Jerawat 3"),
            Article(
                "4",
                "Jerawat 4",
                "Deskripsi Jerawat 4"),
            Article(
                "5",
                "Jerawat 5",
                "Deskripsi Jerawat 5"),
            Article(
                "6",
                "Jerawat 6",
                "Deskripsi Jerawat 6"),
            Article(
                "7",
                "Jerawat 7",
                "Deskripsi Jerawat 7"),
            Article(
                "8",
                "Jerawat 8",
                "Deskripsi Jerawat 8"),
            Article(
                "9",
                "Jerawat 9",
                "Deskripsi Jerawat 9"),
            Article(
                "10",
                "Jerawat 10",
                "Deskripsi Jerawat 10"),
        )
    }
}