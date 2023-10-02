package com.nw.plugins

import com.nw.constants.Constants
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class RoutingTest {
    @Test
    fun testPublicRoute() {
        testApplication {
            val response = client.get("/api/messages/public")
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals("""{"message": "The API doesn't require an access token to share this message."}""", response.bodyAsText())
        }
    }

    @Test
    fun testProtectedRoute() {
        testApplication {
            val response = client.get("/api/messages/protected") {
                header("Authorization", "Bearer ${Constants.ACCESS_TOKEN}")
            }
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals("""{"message": "The API successfully validated your access token."}""", response.bodyAsText())
        }
    }

    @Test
    fun testAdminRoute() {
        testApplication {
            val response = client.get("/api/messages/admin") {
                header("Authorization", "Bearer ${Constants.ACCESS_TOKEN}")
            }
            assertEquals(HttpStatusCode.OK, response.status)
            assertEquals("""{"message": "The API successfully recognized you as an admin."}""", response.bodyAsText())
        }
    }
}
