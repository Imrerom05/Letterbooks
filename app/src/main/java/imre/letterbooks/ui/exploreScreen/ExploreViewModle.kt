package imre.letterbooks.ui.homeScreen

import android.R
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ExploreUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ExploreViewModle() : ViewModel(){
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
}