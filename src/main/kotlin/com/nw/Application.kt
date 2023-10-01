package com.nw

import com.nw.plugins.configureAuth
import com.nw.plugins.configureCORS
import com.nw.plugins.configureRouting
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureCORS()
    configureAuth()
    configureRouting()
}
