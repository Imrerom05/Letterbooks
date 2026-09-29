import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import imre.letterbooks.data.modul.Author
import imre.letterbooks.data.modul.Series
import imre.letterbooks.data.modul.Work
import imre.letterbooks.data.repository.BookRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
data class ExploreUiState(
    val searchQuery: String = "",
    val selectedFilter: SearchFilter = SearchFilter.ALL,

    val works: List<Work> = emptyList(),
    val authors: List<Author> = emptyList(),
    val series: List<Series> = emptyList(),

    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

enum class SearchFilter {
    ALL,
    WORKS,
    AUTHORS,
    SERIES
}

class ExploreViewModel(
    private val repository: BookRepository = BookRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    init {
        observeSearch()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearch() {

        _uiState
            .debounce(400)
            .distinctUntilChanged()
            .onEach {
                if (it.searchQuery.isNotBlank()) {
                    search()
                } else {
                    clearResults()
                }
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            errorMessage = null
        )
    }

    fun selectFilter(filter: SearchFilter) {
        _uiState.value = _uiState.value.copy(
            selectedFilter = filter
        )

        if (_uiState.value.searchQuery.isNotBlank()) {
            search()
        }
    }

    private fun search() {

        val state = _uiState.value

        viewModelScope.launch {

            _uiState.value = state.copy(
                isLoading = true,
                errorMessage = null
            )

            try {

                val type = when (state.selectedFilter) {
                    SearchFilter.ALL -> null
                    SearchFilter.WORKS -> "works"
                    SearchFilter.AUTHORS -> "authors"
                    SearchFilter.SERIES -> "series"
                }

                val result = repository.search(
                    query = state.searchQuery,
                    type = type
                )

                _uiState.value = _uiState.value.copy(
                    works = result.works,
                    authors = result.authors,
                    series = result.series,
                    isLoading = false
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Something went wrong"
                )
            }
        }
    }

    private fun clearResults() {

        _uiState.value = _uiState.value.copy(
            works = emptyList(),
            authors = emptyList(),
            series = emptyList(),
            isLoading = false
        )
    }
}