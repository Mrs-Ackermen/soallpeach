package com.ackermen.countme

import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

var sumOfRequests = 0

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        post("/") {
            val receiveNum = call.receive<String>()
            sumOfRequests += receiveNum.toInt()
            call.respondText("$sumOfRequests")
        }
        get("/count") {
            call.respondText("$sumOfRequests", ContentType.Text.Plain)
        }
    }
}

