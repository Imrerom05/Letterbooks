import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class ExploreUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val query: String = ""
)

class ExploreViewModel(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState




    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

}