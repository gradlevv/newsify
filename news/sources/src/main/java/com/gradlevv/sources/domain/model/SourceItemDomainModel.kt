package com.gradlevv.sources.domain.model

data class SourceItemDomainModel(
    val name: String,
    val description:String,
    val url :String,
    val category:String,
    val language:String,
    val country:String
)