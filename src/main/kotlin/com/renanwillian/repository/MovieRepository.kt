package com.renanwillian.repository

import com.renanwillian.entity.Movie
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MovieRepository: PanacheRepository<Movie> {

    fun existsMovieByProducer(producerId: Long?): Boolean {
        return getEntityManager().createNativeQuery(sqlExistsMovieByProducer())
                                 .setParameter("producerId", producerId)
                                 .singleResult as Boolean
    }

    fun sqlExistsMovieByProducer() =
        """
            SELECT EXISTS (
                SELECT 1 FROM movie
                JOIN movie_producer ON movie_producer.movie_id = movie.movie_id
                WHERE producer_id = :producerId
            )
        """
}