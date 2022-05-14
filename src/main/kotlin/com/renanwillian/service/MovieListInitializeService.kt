package com.renanwillian.service

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.renanwillian.entity.Movie
import com.renanwillian.entity.Producer
import com.renanwillian.entity.Studio
import com.renanwillian.repository.MovieRepository
import com.renanwillian.repository.ProducerRepository
import com.renanwillian.repository.StudioRepository
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import java.util.function.Consumer
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
class MovieListInitializeService {

    @Inject
    lateinit var movieRepository: MovieRepository

    @Inject
    lateinit var producerRepository: ProducerRepository

    @Inject
    lateinit var studioRepository: StudioRepository

    fun importMovieList() {
        val movieList = javaClass.classLoader.getResourceAsStream("movielist.csv")
        val csvParser = CSVParserBuilder().withSeparator(';').build()
        try {
            CSVReaderBuilder(InputStreamReader(Objects.requireNonNull(movieList)))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build().use { reader ->
                    reader.forEach(Consumer { movieData: Array<String> ->
                        importMovie(movieData)
                    })
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun importMovie(movieData: Array<String>) {
        try {
            val movie = Movie()
            movie.year = movieData[0].toInt()
            movie.title = movieData[1]
            movie.studios = splitNames(movieData[2]).map { name -> studioRepository.findByName(name) ?: Studio(name) }
            movie.producers = splitNames(movieData[3]).map { name -> producerRepository.findByName(name) ?: Producer(name) }
            movie.winner = movieData[4] == "yes"
            movieRepository.persist(movie)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun splitNames(names: String) = names.split(", | and ".toRegex())
}