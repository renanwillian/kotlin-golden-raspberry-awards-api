package com.renanwillian.service

import com.renanwillian.entity.Producer
import com.renanwillian.repository.MovieRepository
import com.renanwillian.repository.ProducerRepository
import com.renanwillian.service.dto.ProducerAwardIntervalDTO
import com.renanwillian.service.dto.ProducerDTO
import com.renanwillian.service.exception.NoContentException
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
class ProducerService {

    @Inject
    lateinit var producerRepository: ProducerRepository

    @Inject
    lateinit var movieRepository: MovieRepository

    fun persist(producerDTO: ProducerDTO): ProducerDTO {
        var producer = Producer()
        if (producerDTO.producerId != null) {
            producer = producerRepository.findById(producerDTO.producerId!!) ?: throw NoContentException()
        }
        producer.name = producerDTO.name
        producerRepository.persist(producer)
        return ProducerDTO(producer)
    }

    fun find(producerId: Long): ProducerDTO {
        return ProducerDTO(producerRepository.findById(producerId) ?: throw NoContentException())
    }

    fun delete(producerId: Long) {
        val producer = producerRepository.findById(producerId) ?: throw NoContentException()
        if (movieRepository.existsMovieByProducer(producerId)) throw IllegalStateException()
        producerRepository.delete(producer)
    }

    fun list() = producerRepository.listAll().map(::ProducerDTO)

    fun getProducerAwardInterval(): ProducerAwardIntervalDTO {
        return ProducerAwardIntervalDTO(
            min = producerRepository.listProducerWithMinIntervalAward(),
            max = producerRepository.listProducerWithMaxIntervalAward()
        )
    }
}