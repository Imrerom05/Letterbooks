package imre.letterbooks.data.datasource

import imre.letterbooks.data.modul.Author
import imre.letterbooks.data.modul.FullAuthor
import imre.letterbooks.data.modul.FullSeries
import imre.letterbooks.data.modul.FullWork
import imre.letterbooks.data.modul.SearchResult
import imre.letterbooks.data.modul.Series
import imre.letterbooks.data.modul.Work
import imre.letterbooks.data.modul.WorkInSeries
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BookApi {

    @GET("search")
    suspend fun searchAll(
        @Query("query") query: String,
        @Query("type") type: String? = null
    ): SearchResult

    // -------------------------
    // Works
    // -------------------------

    @GET("works/search")
    suspend fun searchWorks(
        @Query("query") query: String
    ): List<Work>


    @GET("works/{work_id}")
    suspend fun getWork(
        @Path("work_id") workID: Int
    ): Work


    @GET("works/{work_id}/authors")
    suspend fun getWorkAuthors(
        @Path("work_id") workID: Int
    ): List<Author>


    @GET("works/{work_id}/series")
    suspend fun getWorkSeries(
        @Path("work_id") workID: Int
    ): List<Series>


    @GET("works/{work_id}/full")
    suspend fun getFullWork(
        @Path("work_id") workID: Int
    ): FullWork


    // -------------------------
    // Authors
    // -------------------------

    @GET("authors/search")
    suspend fun searchAuthors(
        @Query("query") query: String
    ): List<Author>


    @GET("authors/{author_id}")
    suspend fun getAuthor(
        @Path("author_id") authorID: Int
    ): Author


    @GET("authors/{author_id}/works")
    suspend fun getAuthorWorks(
        @Path("author_id") authorID: Int
    ): List<Work>


    @GET("authors/{author_id}/full")
    suspend fun getFullAuthor(
        @Path("author_id") authorID: Int
    ): FullAuthor


    // -------------------------
    // Series
    // -------------------------

    @GET("series/search")
    suspend fun searchSeries(
        @Query("query") query: String
    ): List<Series>


    @GET("series/{series_id}")
    suspend fun getSeries(
        @Path("series_id") seriesID: Int
    ): Series


    @GET("series/{series_id}/works")
    suspend fun getSeriesWorks(
        @Path("series_id") seriesID: Int
    ): List<WorkInSeries>


    @GET("series/{series_id}/full")
    suspend fun getFullSeries(
        @Path("series_id") seriesID: Int
    ): FullSeries
}