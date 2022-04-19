package com.example.boruto_compose.ui.screen.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.boruto_compose.ui.common.NinjaList
import com.example.boruto_compose.ui.navigation.Screen

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val ninjas = homeViewModel.ninjas.value.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar {
                navController.navigate(Screen.Search.route)
            }
        },
    ) {
        NinjaList(
            navController = navController,
            ninjas = ninjas
        )
    }
}