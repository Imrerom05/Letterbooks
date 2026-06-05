package imre.letterbooks.ui.exploreScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import imre.letterbooks.data.modul.Book
import imre.letterbooks.data.modul.BookItem
import imre.letterbooks.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    fun search(query: String) {
        viewModelScope.launch {
            val searchResult = bookRepository.searchBooks(query)
            _uiState.value = _uiState.value.copy(searchResult = searchResult)
            println("Search query: $query")
            println(searchResult)
        }
    }

    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun clearSearchResult() {
        _uiState.value = _uiState.value.copy(searchResult = emptyList())
    }
}

