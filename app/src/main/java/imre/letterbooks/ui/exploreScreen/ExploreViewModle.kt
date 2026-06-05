import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import imre.letterbooks.data.modul.Book
import imre.letterbooks.data.repository.BookRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class ExploreUiState(
    val searchResult: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val query: String = ""
)

class ExploreViewModel(
    private val bookRepository: BookRepository = BookRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState

    private var searchJob: Job? = null

    fun search(query: String) {
        _uiState.value = _uiState.value.copy(
            query = query,
            isLoading = true,
            errorMessage = null
        )

        // ✅ cancel previous request
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            try {
                delay(300) // debounce
                val searchResult = bookRepository.searchBooks(query)

                // Only apply if this job is still active
                if (isActive) {
                    _uiState.value = _uiState.value.copy(
                        searchResult = searchResult,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                if (isActive) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = e.message,
                        searchResult = emptyList()
                    )
                }
            }
        }
    }

    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun clearSearchResult() {
        searchJob?.cancel()
        _uiState.value = _uiState.value.copy(
            searchResult = emptyList(),
            isLoading = false,
            errorMessage = null
        )
    }
}