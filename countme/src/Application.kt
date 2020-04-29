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
import kotlinx.coroutines.*
// import kotlinx.html.*
// import io.ktor.features.*
// import io.ktor.html.*
// import java.util.*
// import kotlin.system.*

val compute = newSingleThreadContext("Single thread")
var jobs : ArrayList<Deferred<Unit>> = arrayListOf()
var sumOfRequests = 0
fun main(args: Array<String>): Unit {
    val port = System.getenv("PORT")?.toInt() ?: 80
    embeddedServer(Netty, port = port, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    routing {
        post("/") {
            val receiveNum = call.receive<String>()
            call.respondHandlingLongCalculation(receiveNum)
        }
        get("/count") {
            call.respondLatestNumber()
        }
    }
}

private suspend fun ApplicationCall.respondHandlingLongCalculation(receiveNum : String) {
    jobs.add(CoroutineScope(compute).async {
        sumOfRequests += receiveNum.toInt()
    })
}

private suspend fun ApplicationCall.respondLatestNumber(){
    
    jobs.awaitAll()
    respondText("$sumOfRequests", ContentType.Text.Plain)   
    
}
