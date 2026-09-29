package imre.letterbooks.data.modul

import com.google.gson.annotations.SerializedName


data class SearchResult(
    @SerializedName("works")
    val works: List<Work> = emptyList(),

    @SerializedName("authors")
    val authors: List<Author> = emptyList(),

    @SerializedName("series")
    val series: List<Series> = emptyList()
)

data class Work(
    @SerializedName("work_id")
    val workID: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("original_year")
    val originalYear: Int? = null,

    @SerializedName("cover_image_url")
    val coverImageURL: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null
)

data class AuthorWork(
    @SerializedName("work_id")
    val workID: Int = 0,

    @SerializedName("author_id")
    val authorID: Int = 0
)

data class Author(
    @SerializedName("author_id")
    val authorID: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("birth")
    val birth: Int? = null,

    @SerializedName("death")
    val death: Int? = null,

    @SerializedName("image_url")
    val imageURL: String? = null,

    @SerializedName("description")
    val description: String? = null
)

data class Series(
    @SerializedName("series_id")
    val seriesID: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("description")
    val description: String? = null
)

data class SeriesWork(
    @SerializedName("series_id")
    val seriesID: Int = 0,

    @SerializedName("work_id")
    val workID: Int = 0,

    @SerializedName("position")
    val position: Int? = null
)


data class FullWork(
    @SerializedName("work_id")
    val workID: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("original_year")
    val originalYear: Int? = null,

    @SerializedName("cover_image_url")
    val coverImageURL: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("authors")
    val authors: List<Author> = emptyList(),

    @SerializedName("series")
    val series: List<SeriesWork> = emptyList()
)



data class FullAuthor(
    @SerializedName("author_id")
    val authorID: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("birth")
    val birth: Int? = null,

    @SerializedName("death")
    val death: Int? = null,

    @SerializedName("image_url")
    val imageURL: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("works")
    val works: List<Work> = emptyList()
)


data class FullSeries(
    @SerializedName("series_id")
    val seriesID: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("works")
    val works: List<WorkInSeries> = emptyList()
)




data class WorkInSeries(
    @SerializedName("work_id")
    val workID: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("original_year")
    val originalYear: Int? = null,

    @SerializedName("cover_image_url")
    val coverImageURL: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("position")
    val position: Int? = null
)


