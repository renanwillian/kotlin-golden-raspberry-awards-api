package com.renanwillian.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

@QuarkusTest
class ProducerEndpointTest {

    @Test
    fun getProducerAwardInterval() {
        given()
            .`when`().get("/producer/award-interval")
            .then()
            .statusCode(200)
            .body("min[0].producer", CoreMatchers.`is`("Joel Silver"))
            .body("min[0].interval", CoreMatchers.`is`(1))
            .body("min[0].previousWin", CoreMatchers.`is`(1990))
            .body("min[0].followingWin", CoreMatchers.`is`(1991))
            .body("max[0].producer", CoreMatchers.`is`("Matthew Vaughn"))
            .body("max[0].interval", CoreMatchers.`is`(13))
            .body("max[0].previousWin", CoreMatchers.`is`(2002))
            .body("max[0].followingWin", CoreMatchers.`is`(2015));
    }
}