package com.renanwillian.service.dto

import com.renanwillian.entity.Producer
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(name = "Producer")
data class ProducerDTO(var producerId: Long?, var name: String) {
    constructor(producer: Producer): this(producer.producerId, producer.name)
}