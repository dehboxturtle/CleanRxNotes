package com.dehboxturtle.data.net.dto

import com.google.gson.annotations.SerializedName

data class NoteDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("html_url") val url: String
)
