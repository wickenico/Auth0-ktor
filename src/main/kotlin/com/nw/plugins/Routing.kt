package com.nw.plugins

import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/api/messages/public") {
            call.respondText(
                """{"message": "The API doesn't require an access token to share this message."}""",
                contentType = ContentType.Application.Json
            )
        }
    }

    routing {
        authenticate("auth0") {
            get("/api/messages/protected") {
                call.respondText(
                    """{"message": "The API successfully validated your access token."}""",
                    contentType = ContentType.Application.Json
                )
            }
        }
    }

    routing {
        authenticate("auth0-admin") {
            get("/api/messages/admin") {
                call.respondText(
                    """{"message": "The API successfully recognized you as an admin."}""",
                    contentType = ContentType.Application.Json
                )
            }
        }
    }
}
