package imre.letterbooks.data.datasource

import imre.letterbooks.data.modul.Book
import imre.letterbooks.data.modul.SearchResponse
import imre.letterbooks.data.modul.WorkDetail
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface OpenLibraryApi {
    suspend fun searchBooks(query: String): SearchResponse
}

class OpenLibraryApiImpl(
    private val client: HttpClient
) : OpenLibraryApi {

    override suspend fun searchBooks(query: String): SearchResponse {
        return client.get("https://openlibrary.org/search.json") {
            parameter("q", query)
            parameter("limit", 10)
        }.body()
    }

    suspend fun getBookDetails(workId: String): WorkDetail {
        return client.get("https://openlibrary.org$workId.json").body()
    }
}


object NetworkClient {

    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    val httpClient = HttpClient(CIO) {

        install(ContentNegotiation) {
            json(json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 10_000
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 10_000
        }
    }
}