package com.example.powerliftingtracker.ui.component.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.powerliftingtracker.R
import com.google.firebase.example.makeitso.ui.shared.CenterTopAppBar
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(openSettingsScreen: () -> Unit, viewModel: HomeViewModel= hiltViewModel()) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterTopAppBar(
                title = stringResource(R.string.app_name),
                icon = Icons.Filled.Settings,
                iconDescription = "Settings screen icon",
                action = openSettingsScreen,
                scrollBehavior = scrollBehavior
            )
        },
    ) {innerPadding->
        ConstraintLayout(modifier= Modifier.fillMaxSize().padding(
            top= innerPadding.calculateTopPadding(),
            start= 4.dp,
            end=4.dp,
            bottom=4.dp
        )){
            Text("BigDom")
        }

    }

}

@Composable
fun HomeScreenContent() {

}