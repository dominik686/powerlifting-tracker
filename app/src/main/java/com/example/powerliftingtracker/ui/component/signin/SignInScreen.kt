package com.example.powerliftingtracker.ui.theme.component.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.powerliftingtracker.data.model.ErrorMessage
import com.example.powerliftingtracker.ui.component.signin.SignInViewModel

@Composable
fun LoginScreen(openHomeScreen: () -> Unit,
                openSignUpScreen: () -> Unit,
                showErrorSnackbar: (ErrorMessage) -> Unit,
                viewModel: SignInViewModel = hiltViewModel()){

}