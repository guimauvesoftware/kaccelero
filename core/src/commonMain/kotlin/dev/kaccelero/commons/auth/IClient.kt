package dev.kaccelero.commons.auth

interface IClient {

    val clientId: String
    val clientSecret: String
    val redirectUri: String

}
