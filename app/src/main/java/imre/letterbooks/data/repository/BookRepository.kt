package imre.letterbooks.data.repository

import imre.letterbooks.data.datasource.BookApi
import imre.letterbooks.data.datasource.RetrofitClient
import imre.letterbooks.data.modul.Author
import imre.letterbooks.data.modul.FullAuthor
import imre.letterbooks.data.modul.FullSeries
import imre.letterbooks.data.modul.FullWork
import imre.letterbooks.data.modul.SearchResult
import imre.letterbooks.data.modul.Series
import imre.letterbooks.data.modul.WorkInSeries
import imre.letterbooks.data.modul.Work

class BookRepository(
    private val api: BookApi = RetrofitClient.bookApi
) {

    suspend fun search(
        query: String,
        type: String? = null
    ): SearchResult {
        return api.searchAll(query, type)
    }

    // -------------------------
    // Works
    // -------------------------

    suspend fun searchWorks(query: String): List<Work> {
        return api.searchWorks(query)
    }

    suspend fun getWork(workID: Int): Work {
        return api.getWork(workID)
    }

    suspend fun getWorkAuthors(workID: Int): List<Author> {
        return api.getWorkAuthors(workID)
    }

    suspend fun getWorkSeries(workID: Int): List<Series> {
        return api.getWorkSeries(workID)
    }

    suspend fun getFullWork(workID: Int): FullWork {
        return api.getFullWork(workID)
    }


    // -------------------------
    // Authors
    // -------------------------

    suspend fun searchAuthors(query: String): List<Author> {
        return api.searchAuthors(query)
    }

    suspend fun getAuthor(authorID: Int): Author {
        return api.getAuthor(authorID)
    }

    suspend fun getAuthorWorks(authorID: Int): List<Work> {
        return api.getAuthorWorks(authorID)
    }

    suspend fun getFullAuthor(authorID: Int): FullAuthor {
        return api.getFullAuthor(authorID)
    }


    // -------------------------
    // Series
    // -------------------------

    suspend fun searchSeries(query: String): List<Series> {
        return api.searchSeries(query)
    }

    suspend fun getSeries(seriesID: Int): Series {
        return api.getSeries(seriesID)
    }

    suspend fun getSeriesWorks(seriesID: Int): List<WorkInSeries> {
        return api.getSeriesWorks(seriesID)
    }

    suspend fun getFullSeries(seriesID: Int): FullSeries {
        return api.getFullSeries(seriesID)
    }
}