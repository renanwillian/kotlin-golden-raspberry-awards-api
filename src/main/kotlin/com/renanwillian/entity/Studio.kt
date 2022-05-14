package com.renanwillian.entity

import javax.persistence.*

@Entity
class Studio {
    constructor()
    constructor(name: String) {
        this.name = name
    }

    @Id
    @SequenceGenerator(allocationSize = 1, name = "studio_seq", sequenceName = "studio_studio_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studio_seq")
    @Column(name = "studio_id", nullable = false)
    var studioId: Long? = null

    @Column(nullable = false)
    lateinit var name: String
}