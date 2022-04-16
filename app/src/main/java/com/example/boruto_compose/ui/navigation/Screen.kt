package com.example.boruto_compose.ui.navigation

const val NINJA_ID = "ninjaId"

sealed class Screen(val route : String){
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("detail_screen/{$NINJA_ID}"){
        fun passNinjaId(id : Int) : String{
            return route.replace("{$NINJA_ID}","$id")
        }
    }
}
