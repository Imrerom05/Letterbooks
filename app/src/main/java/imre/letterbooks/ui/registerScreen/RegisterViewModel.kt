package imre.letterbooks.ui.registerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import imre.letterbooks.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class RegisterUiState(
    val mail: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class RegisterViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onMailChange(value: String) {
        _uiState.value = _uiState.value.copy(mail = value)
    }

    fun onUsernameChange(value: String) {
        _uiState.value = _uiState.value.copy(username = value)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = value)
    }

    fun setError(message: String?) {
        _uiState.value = _uiState.value.copy(errorMessage = message)
    }

    fun setLoading(value: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = value)
    }


        fun register(
            onSuccess: () -> Unit
        ) {

            val state = _uiState.value

            when {
                state.mail.isBlank() ||
                        state.username.isBlank() ||
                        state.password.isBlank() ||
                        state.confirmPassword.isBlank() -> {

                    setError("Please fill in all fields")
                    return
                }

                state.password != state.confirmPassword -> {

                    setError("Passwords do not match")
                    return
                }
            }

            viewModelScope.launch {

                setLoading(true)

                repository.register(
                    state.mail,
                    state.password,
                    state.username
                )
                    .onSuccess {
                        setLoading(false)
                        onSuccess()
                    }
                    .onFailure {
                        setLoading(false)
                        setError(it.message)
                    }
            }
        }
    }
