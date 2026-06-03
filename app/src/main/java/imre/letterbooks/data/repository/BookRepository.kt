package imre.letterbooks.data.repository

import imre.letterbooks.BuildConfig
import imre.letterbooks.data.modul.BookItem


class BookRepository {
    suspend fun searchBooks(
        query: String
    ): List<BookItem> {
        return try {
            BooksApiClient.api.searchBooks(
                query,
                BuildConfig.GOOGLE_BOOKS_API_KEY
            ).items
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}