package com.gradlevv.list.data.model

import com.google.gson.annotations.SerializedName
import com.gradlevv.core.util.DateTimeHelper.simpleDateFormat
import com.gradlevv.list.domain.TopHeadLinesItem


data class TopHeadLinesResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("articles")
    val articleList: List<Article>?
) {
    data class Article(
        @SerializedName("source")
        val source: Source?,
        @SerializedName("author")
        val author: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("urlToImage")
        val urlToImage: String?,
        @SerializedName("publishedAt")
        val publishedAt: String?,
        @SerializedName("content")
        val content: String?
    )

    data class Source(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?
    )
}

fun TopHeadLinesResponse.Article.toDomain(): TopHeadLinesItem {
    return TopHeadLinesItem(
        source = TopHeadLinesItem.SourceItem(
            id = source?.id ?: System.currentTimeMillis().toString(),
            name = source?.name ?: ""
        ),
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        imageUrl = urlToImage ?: "",
        publishedAt = simpleDateFormat(publishedAt ?: "") ?: "",
        content = content ?: ""
    )
}

fun List<TopHeadLinesResponse.Article>.toDomain(): List<TopHeadLinesItem> {
    return this.map { it.toDomain() }
}