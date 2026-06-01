package imre.letterbooks.ui.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import imre.letterbooks.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val mail: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onMailChange(value: String) {
        _uiState.value = _uiState.value.copy(mail = value)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun login(
        onSuccess: () -> Unit
    ) {
        val state = _uiState.value

        if (state.mail.isBlank() || state.password.isBlank()) {
            _uiState.value = state.copy(errorMessage = "Please fill in all fields")
            return
        }

        viewModelScope.launch {
            _uiState.value =
                state.copy(
                    isLoading = true,
                    errorMessage = null
                )

            repository.login(
                state.mail,
                state.password
            )
                .onSuccess {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false
                        )
                    onSuccess()
                }
                .onFailure {
                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                }
        }
    }
}





