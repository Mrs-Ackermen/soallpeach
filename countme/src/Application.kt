package com.ackermen.countme

import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main(args: Array<String>): Unit {
    val server = embeddedServer(Netty, port = 80) {
        routing {
            post("/") {
                val receiveNum = call.receive<String>()
                sumOfRequests += receiveNum.toInt()
            }
            get("/count") {
                call.respondText("$sumOfRequests", ContentType.Text.Plain)
            }
        }
    }
    server.start(wait = true)
}

var sumOfRequests = 0

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {}

