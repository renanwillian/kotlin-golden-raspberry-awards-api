package com.renanwillian.entity

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
class Movie {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "movie_seq", sequenceName = "movie_movie_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "movie_seq")
    @Column(name = "movie_id", nullable = false)
    var movieId: Long? = null

    @Column(nullable = false)
    var year: Int? = null

    @Column(nullable = false)
    lateinit var title: String

    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
        name = "movie_studio",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "studio_id")]
    )
    var studios: List<Studio>? = null

    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
        name = "movie_producer",
        joinColumns = [JoinColumn(name = "movie_id")],
        inverseJoinColumns = [JoinColumn(name = "producer_id")]
    )
    var producers: List<Producer>? = null

    @Column(nullable = false)
    var winner: Boolean? = null
}