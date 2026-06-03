package imre.letterbooks.data.repository

import imre.letterbooks.data.modul.BookItem

class BookRepository {

    suspend fun searchBooks(
        query: String
    ): List<BookItem> {

        return try {
            ApiClient.api
                .searchBooks(query)
                .items

        } catch (e: Exception) {
            emptyList()
        }
    }
}