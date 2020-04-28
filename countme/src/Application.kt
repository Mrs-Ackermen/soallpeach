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
    val port = System.getenv("PORT")?.toInt() ?: 80
    embeddedServer(Netty, port = port, module = Application::mainModule).start(wait = true)
}

var sumOfRequests = 0

fun Application.mainModule() {
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

