package com.renanwillian.util

import javax.ws.rs.core.Response

object RestUtils {

    fun okOrNoContent(list: List<Any>): Response {
        if (list.isEmpty()) return Response.noContent().build()
        return Response.ok(list).build()
    }
}