package dev.kaccelero.routers

import io.ktor.server.application.*

class KtorCall(
    val call: ApplicationCall,
) : ICall
