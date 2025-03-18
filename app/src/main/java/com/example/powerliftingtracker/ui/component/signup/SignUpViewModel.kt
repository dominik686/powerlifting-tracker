package com.example.powerliftingtracker.ui.component.signup

import com.example.powerliftingtracker.MainViewModel
import com.example.powerliftingtracker.R
import com.example.powerliftingtracker.data.auth.AuthRepository
import com.example.powerliftingtracker.data.model.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    MainViewModel() {
    private val _shouldRestartApp = MutableStateFlow(false)
    val shouldRestartApp: StateFlow<Boolean>
        get() = _shouldRestartApp.asStateFlow()

    fun signUp(
        email: String, password: String,
        repeatPassword: String,
        showErrorSnackbar: (ErrorMessage) -> Unit
    ) {
        if (!email.isValidEmail()) {
            showErrorSnackbar(ErrorMessage.IdError(R.string.invalid_email))
        }

        if (!password.isValidPassword()) {
            showErrorSnackbar(ErrorMessage.IdError(R.string.invalid_password))
            return
        }

        launchCatching(showErrorSnackbar) {
            authRepository.signUp(email, password)
            _shouldRestartApp.value = true
        }
    }
}