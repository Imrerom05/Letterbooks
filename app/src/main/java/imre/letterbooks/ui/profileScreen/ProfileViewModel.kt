package imre.letterbooks.ui.profileScreen

import androidx.lifecycle.ViewModel
import imre.letterbooks.data.AuthRepository
import imre.letterbooks.data.FirebaseRepository
import imre.letterbooks.data.modul.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ProfileUiState(
    val user: User? = null
)

class ProfileViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val fireRepository: FirebaseRepository = FirebaseRepository()
) : ViewModel(){
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        observeUser()
    }

    private fun observeUser() {
        fireRepository.observeUser { user ->
            _uiState.value = _uiState.value.copy(user = user)
        }
    }

    fun logout() {
        authRepository.logout()
    }




}
