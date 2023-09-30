package com.nw.plugins

import com.auth0.jwk.JwkProviderBuilder
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import java.util.concurrent.TimeUnit

fun validateCredentials(credential: JWTCredential, permission: String? = null): JWTPrincipal? {

    val dotenv = dotenv()
    val audience = dotenv["AUTH0_AUDIENCE"]

    val containsAudience = credential.payload.audience.contains(audience)
    val containsScope = permission.isNullOrBlank() ||
            credential.payload.claims["permissions"]?.asArray(String::class.java)?.contains(permission) == true

    if (containsAudience && containsScope) {
        return JWTPrincipal(credential.payload)
    }

    return null
}

fun Application.configureAuth() {

    val dotenv = dotenv()
    val issuer = dotenv["AUTH0_ISSUER"]

    val jwkProvider = JwkProviderBuilder(issuer)
        .cached(10, 24, TimeUnit.HOURS)
        .rateLimited(10, 1, TimeUnit.MINUTES)
        .build()

    install(Authentication) {
        jwt("auth0") {
            verifier(jwkProvider, issuer)
            validate { credential -> validateCredentials(credential) }
        }

        jwt("auth0-admin") {
            verifier(jwkProvider, issuer)
            validate { credential -> validateCredentials(credential, "read:admin-messages") }
        }
    }
}