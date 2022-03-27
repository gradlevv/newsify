package com.gradlevv.list.domain

data class TopHeadLinesItem(
    val source: SourceItem,
    val author:String,
    val title: String,
    val description:String,
    val url: String,
    val imageUrl:String,
    val publishedAt:String,
    val content:String?
){
    data class SourceItem(
        val id: String,
        val name: String
    )
}