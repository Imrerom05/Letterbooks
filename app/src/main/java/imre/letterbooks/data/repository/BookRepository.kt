package imre.letterbooks.data.repository

import imre.letterbooks.BuildConfig
import imre.letterbooks.data.datasource.NetworkClient
import imre.letterbooks.data.datasource.OpenLibraryApiImpl
import imre.letterbooks.data.modul.Book
import imre.letterbooks.data.modul.BookDoc
import imre.letterbooks.data.modul.BookItem


class BookRepository {

    val client = NetworkClient.httpClient
    val openLibraryApi = OpenLibraryApiImpl(client)
    suspend fun searchBooks(query: String): List<Book> {
        val response = openLibraryApi.searchBooks(query)

        return response.docs
            .distinctBy { it.key }
            .map { it.toDomain() }
    }

    suspend fun searchBooksGoogle(
        query: String
    ): List<BookItem> {
        return try {
            GoogleBooksApiClient.api.searchBooks(
                query,
                BuildConfig.GOOGLE_BOOKS_API_KEY
            ).items
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}


fun BookDoc.toDomain(): Book {
    return Book(
        workId = key,
        title = title,
        author = author_name?.firstOrNull() ?: "Unknown",
        firstPublishYear = first_publish_year,
        coverUrl = cover_i?.let {
            "https://covers.openlibrary.org/b/id/$it-L.jpg"
        }
    )
}