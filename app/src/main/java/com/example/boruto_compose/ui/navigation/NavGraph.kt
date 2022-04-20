package com.example.boruto_compose.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.boruto_compose.ui.screen.details.DetailsScreen
import com.example.boruto_compose.ui.screen.home.HomeScreen
import com.example.boruto_compose.ui.screen.search.SearchScreen
import com.example.boruto_compose.ui.screen.splash.SplashScreen
import com.example.boruto_compose.ui.screen.welcome.WelcomeScreen
import com.example.boruto_compose.util.Constant.DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
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
            SearchScreen(navController = navController)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(
                    name = DETAILS_ARGUMENT_KEY
                ){
                    type = NavType.IntType
                }
            )
        ){
            DetailsScreen(navController = navController)
        }
    }
}