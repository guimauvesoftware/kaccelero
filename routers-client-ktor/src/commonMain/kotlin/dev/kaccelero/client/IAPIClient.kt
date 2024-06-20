package dev.kaccelero.client

import dev.kaccelero.commons.auth.IGetTokenUseCase
import dev.kaccelero.commons.auth.ILogoutUseCase
import dev.kaccelero.commons.auth.IRenewTokenUseCase
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

interface IAPIClient {

    val baseUrl: String
    val getTokenUseCase: IGetTokenUseCase?
    val renewTokenUseCase: IRenewTokenUseCase?
    val logoutUseCase: ILogoutUseCase?

    suspend fun request(
        method: HttpMethod,
        path: String,
        builder: HttpRequestBuilder.() -> Unit = {},
    ): HttpResponse

}
