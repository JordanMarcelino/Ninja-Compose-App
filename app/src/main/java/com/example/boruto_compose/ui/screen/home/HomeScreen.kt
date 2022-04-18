package com.example.boruto_compose.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.boruto_compose.ui.component.RatingStar
import com.example.boruto_compose.ui.theme.spacing

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController
) {

    Scaffold(
        topBar = {
            HomeTopBar {

            }
        },
    ) {
        RatingStar(
            modifier = Modifier.padding(MaterialTheme.spacing.large),
            rating = 4.5)
    }
}