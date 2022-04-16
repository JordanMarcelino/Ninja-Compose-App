package com.example.boruto_compose.ui.navigation

import com.example.boruto_compose.util.Constant.DETAILS_ARGUMENT_KEY


sealed class Screen(val route : String){
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Search : Screen("search_screen")
    object Details : Screen("detail_screen/{$DETAILS_ARGUMENT_KEY}"){
        fun passNinjaId(id : Int) : String{
            return route.replace("{$DETAILS_ARGUMENT_KEY}","$id")
        }
    }
}
