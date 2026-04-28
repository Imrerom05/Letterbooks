package imre.letterbooks.ui.loginScreen

import androidx.lifecycle.ViewModel
import imre.letterbooks.data.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class LoginUiState(
    val mailUsername: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class LoginViewModel(
    private val repository: MainRepository = MainRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onUsernameChange(value: String) {
        _uiState.value = _uiState.value.copy(mailUsername = value)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun setError(message: String?) {
        _uiState.value = _uiState.value.copy(errorMessage = message)
    }

    fun setLoading(value: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = value)
    }

    fun login() {
        val state = _uiState.value

        if (state.mailUsername.isBlank() || state.password.isBlank()) {
            setError("Please fill in all fields")
            return
        }

        setError(null)
        setLoading(true)


        // TODO: call repository here
        // repository.login(state.mailUsername, state.password)
    }
}
