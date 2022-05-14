package com.renanwillian.repository

import com.renanwillian.entity.Producer
import com.renanwillian.service.dto.ProducerAwardDTO
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProducerRepository: PanacheRepository<Producer> {

    fun findByName(name: String) = find("name", name).firstResult()

    fun listProducerWithMaxIntervalAward(): List<ProducerAwardDTO> {
        val resultList = getEntityManager().createNativeQuery(sqlProducerWithMaxIntervalAward()).resultList
        return mapToProducerAwardDTO(resultList as List<Array<Any>?>)
    }

    fun listProducerWithMinIntervalAward(): List<ProducerAwardDTO> {
        val resultList = getEntityManager().createNativeQuery(sqlProducerWithMinIntervalAward()).resultList
        return mapToProducerAwardDTO(resultList as List<Array<Any>?>)
    }

    private fun mapToProducerAwardDTO(resultList: List<Array<Any>?>): List<ProducerAwardDTO> {
        return resultList.map { objects: Array<Any>? ->
            ProducerAwardDTO(
                producer = objects!![0] as String,
                previousWin = objects[1] as Int,
                followingWin = objects[2] as Int,
                interval = objects[3] as Int
            )
        }
    }

    private fun sqlProducerIntervalAward() =
        """
            WITH producer_row_number AS ( 
                SELECT ROW_NUMBER() OVER (ORDER BY producer_id, year) AS row_number, producer_id, year 
                FROM movie_producer 
                JOIN movie ON movie_producer.movie_id = movie.movie_id 
                WHERE winner IS TRUE 
                ORDER BY producer_id, year 
            ), 
            award_interval AS ( 
                SELECT p1.producer_id, p1.year AS previous, p2.year AS following, p2.year - p1.year AS years_between 
                FROM producer_row_number p1 
                JOIN producer_row_number p2 ON p1.producer_id = p2.producer_id AND p1.row_number + 1 = p2.row_number 
            ) 
            SELECT name, previous, following, years_between 
            FROM award_interval 
            JOIN producer ON award_interval.producer_id = producer.producer_id
        """

    private fun sqlProducerWithMaxIntervalAward() = sqlProducerIntervalAward() + "WHERE years_between = (SELECT MAX(years_between) FROM award_interval) "
    private fun sqlProducerWithMinIntervalAward() = sqlProducerIntervalAward() + "WHERE years_between = (SELECT MIN(years_between) FROM award_interval) "
}