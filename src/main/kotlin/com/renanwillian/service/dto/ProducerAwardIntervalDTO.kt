package com.renanwillian.service.dto

data class ProducerAwardIntervalDTO(
    val min: List<ProducerAwardDTO>,
    val max: List<ProducerAwardDTO>,
)