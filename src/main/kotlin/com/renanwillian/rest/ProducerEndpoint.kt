package com.renanwillian.rest

import com.renanwillian.service.ProducerService
import com.renanwillian.service.dto.ProducerDTO
import com.renanwillian.util.RestUtils.okOrNoContent
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.inject.Inject
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/producer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Producer")
class ProducerEndpoint {

    @Inject
    lateinit var producerService: ProducerService

    @GET
    fun list() = okOrNoContent(producerService.list())

    @GET
    @Path("/{producerId:[0-9]+}")
    fun find(@PathParam producerId: Long) = producerService.find(producerId)

    @POST
    fun persist(@Valid producer: ProducerDTO) = producerService.persist(producer)

    @PUT
    @Path("/{producerId:[0-9]+}")
    fun persist(@PathParam producerId: Long, @Valid producer: ProducerDTO): ProducerDTO {
        producer.producerId = producerId
        return producerService.persist(producer)
    }

    @DELETE
    @Path("/{producerId:[0-9]+}")
    fun persist(@PathParam producerId: Long) = producerService.delete(producerId)

    @GET
    @Path("/award-interval")
    fun getProducerAwardInterval() = producerService.getProducerAwardInterval()
}