# Auth0 Ktor PoC

This is an PoC project demonstrating how to integrate Auth0 authentication and authorization with a Ktor-based web application.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
- - [Links](#links)

## Getting Started

Follow these steps to set up and run the Auth0 Ktor PoC locally.

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Kotlin and Ktor development environment set up.
- An Auth0 account with a configured application.

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/wickenico/Auth0-ktor.git

1. Move to directory:

    ```bash
    cd Auth0-ktor

1. Start Ktor Application:

      ```bash
    ./gradlew run

### Configuration

1. Create a **_.env_** file in root of the project. Replace the values with your own credentials:

    ```bash
   AUTH0_AUDIENCE="YOUR_AUDIENCE"
   AUTH0_ISSUER="YOUR_ISSUER"
   
- **Audience** is the identifier of the API you want to call. It can be found in the API section of the Auth0 dashboard.
- **Issuer** is the URL of the authorization server that will perform authentication. It can be found in the Settings section of the Auth0 dashboard.

## Links

- https://auth0.com/blog/creating-an-http-api-with-ktor-and-kotlin/
- https://auth0.com/blog/adding-auth0-authorization-to-a-ktor-http-api/
- https://auth0.com/blog/adding-auth0-rbac-authorization-to-a-ktor-api/