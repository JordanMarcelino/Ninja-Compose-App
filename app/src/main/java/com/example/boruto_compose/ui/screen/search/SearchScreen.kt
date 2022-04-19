package com.example.boruto_compose.ui.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.boruto_compose.ui.common.NinjaList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val query by searchViewModel.query
    val ninjas = searchViewModel.ninjas.collectAsLazyPagingItems()

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
        }
    ) {
        NinjaList(
            navController = navController,
            ninjas = ninjas
        )
    }

}