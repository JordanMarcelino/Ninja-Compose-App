package com.example.boruto_compose.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.boruto_compose.ui.screen.home.HomeScreen
import com.example.boruto_compose.ui.screen.splash.SplashScreen
import com.example.boruto_compose.ui.screen.welcome.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun NavGraph(
    navController : NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navController)
        }

        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }

        composable(route = Screen.Search.route){

        }

        composable(route = Screen.Details.route){

        }
    }
}