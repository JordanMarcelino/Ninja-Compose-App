package com.example.boruto_compose.ui.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.boruto_compose.ui.common.NinjaList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val query by searchViewModel.query
    val ninjas = searchViewModel.ninjas.collectAsLazyPagingItems()

    val systemUi = rememberSystemUiController()
    val statusBarColor = MaterialTheme.colorScheme.primary
    SideEffect {
        systemUi.setStatusBarColor(
            color = statusBarColor
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            SearchTopBar(
                text = query,
                onValueChange = searchViewModel::onQueryChange,
                onSearchClicked = { searchViewModel.searchNinja() },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        NinjaList(
            navController = navController,
            ninjas = ninjas
        )
    }
}