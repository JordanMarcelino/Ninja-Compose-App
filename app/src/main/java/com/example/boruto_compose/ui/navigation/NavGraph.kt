package com.example.boruto_compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController : NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){

        }

        composable(route = Screen.Welcome.route){

        }

        composable(route = Screen.Home.route){

        }

        composable(route = Screen.Search.route){

        }

        composable(route = Screen.Details.route){

        }
    }
}