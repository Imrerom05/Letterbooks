package imre.letterbooks.ui.homeScreen

import android.R
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeUiState(
    val user: R.string
)

class HomeViewModel() : ViewModel(){
    private val _uiState = MutableStateFlow(HomeUiState(
        user = TODO()
    ))
    val uiState: StateFlow<HomeUiState> = _uiState

}