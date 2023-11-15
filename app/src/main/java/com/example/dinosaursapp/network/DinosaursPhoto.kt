package com.example.dinosaursapp.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DinosaursPhoto(
    val name: String,
    val length: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
