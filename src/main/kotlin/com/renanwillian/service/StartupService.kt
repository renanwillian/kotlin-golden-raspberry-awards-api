package com.renanwillian.service

import io.quarkus.runtime.StartupEvent
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
class StartupService {

    @Inject
    lateinit var movieListInitializeService: MovieListInitializeService

    fun onStart(@Observes event: StartupEvent) {
        movieListInitializeService.importMovieList()
    }
}