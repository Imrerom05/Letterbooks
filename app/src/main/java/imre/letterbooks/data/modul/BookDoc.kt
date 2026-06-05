package imre.letterbooks.data.modul

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val docs: List<BookDoc>
)

@Serializable
data class BookDoc(
    val key: String,
    val title: String,
    val author_name: List<String>? = null,
    val first_publish_year: Int? = null,
    val cover_i: Int? = null
)

data class BookSugestion(
    val workId: String,
    val title: String,
    val author: String,
    val firstPublishYear: Int?,
    val coverUrl: String?,
)

@Serializable
data class WorkDetail(
    val title: String,
    val description: String? = null
)