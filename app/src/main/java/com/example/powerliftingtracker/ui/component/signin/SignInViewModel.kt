package com.example.powerliftingtracker.ui.component.signin

import com.example.powerliftingtracker.MainViewModel
import com.example.powerliftingtracker.data.auth.AuthRepository
import com.example.powerliftingtracker.data.model.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : MainViewModel(){
    private val _shouldRestartApp = MutableStateFlow(false)
    val shouldRestartApp: StateFlow<Boolean>
        get() = _shouldRestartApp.asStateFlow()

    fun signIn(
        email: String,
        password:String,
        showErrorSnackbar: (ErrorMessage) -> Unit
    ){
        launchCatching(showErrorSnackbar){
            authRepository.signIn(email, password)
            _shouldRestartApp.value = true
        }
    }
}