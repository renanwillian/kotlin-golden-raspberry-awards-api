package com.renanwillian.service.dto

data class ProducerAwardDTO(
    val producer: String,
    val interval: Int,
    val previousWin: Int,
    val followingWin: Int,
)