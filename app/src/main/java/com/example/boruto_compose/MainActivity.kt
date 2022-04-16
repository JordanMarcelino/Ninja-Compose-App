package com.example.boruto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.boruto_compose.ui.navigation.NavGraph
import com.example.boruto_compose.ui.theme.BorutoComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BorutoComposeTheme {
                navController = rememberNavController()

                NavGraph(navController = navController)
            }
        }
    }
}
