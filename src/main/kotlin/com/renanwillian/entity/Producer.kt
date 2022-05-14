package com.renanwillian.entity

import javax.persistence.*

@Entity
class Producer {
    constructor()
    constructor(name: String) {
        this.name = name
    }

    @Id
    @SequenceGenerator(allocationSize = 1, name = "producer_seq", sequenceName = "producer_producer_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "producer_seq")
    @Column(name = "producer_id", nullable = false)
    var producerId: Long? = null

    @Column(nullable = false)
    lateinit var name: String
}