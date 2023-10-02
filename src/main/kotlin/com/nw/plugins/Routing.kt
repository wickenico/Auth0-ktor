package com.nw.plugins

import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.util.pipeline.PipelineContext

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        route("/api/messages") {
            get("/public") {
                respondWithMessage("The API doesn't require an access token to share this message.")
            }

            authenticate("auth0") {
                get("/protected") {
                    respondWithMessage("The API successfully validated your access token.")
                }
            }

            authenticate("auth0-admin") {
                get("/admin") {
                    respondWithMessage("The API successfully recognized you as an admin.")
                }
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.respondWithMessage(message: String) {
    call.respondText(
        """{"message": "$message"}""",
        ContentType.Application.Json
    )
}
