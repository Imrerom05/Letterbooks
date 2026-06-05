package imre.letterbooks.data.modul


data class BookResponse(
    val items: List<BookItem> = emptyList()
)

data class BookItem(
    val id: String = "",
    val volumeInfo: VolumeInfo = VolumeInfo()
)

data class VolumeInfo(
    val title: String = "",
    val authors: List<String> = emptyList(),
    val description: String = "",
    val publishedDate: String = "",
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String = ""
)