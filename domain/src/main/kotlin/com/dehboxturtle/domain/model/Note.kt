package com.dehboxturtle.domain.model

data class Note(
    val id: Long,
    val name: String,
    val description: String,
    val url: String,
    val userName: String
)