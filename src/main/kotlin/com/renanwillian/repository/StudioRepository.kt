package com.renanwillian.repository

import com.renanwillian.entity.Studio
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudioRepository: PanacheRepository<Studio> {

    fun findByName(name: String) = find("name", name).firstResult()
}