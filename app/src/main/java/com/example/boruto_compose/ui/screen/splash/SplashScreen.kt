package com.example.boruto_compose.ui.screen.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.boruto_compose.R
import com.example.boruto_compose.ui.navigation.Screen

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val degrees = remember { Animatable(initialValue = 0f) }
    val shouldNavigate by splashViewModel.shouldNavigate

    LaunchedEffect(key1 = true){
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )

        if (shouldNavigate){
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }else{
            navController.popBackStack()
            navController.navigate(Screen.Welcome.route)
        }
    }

    Splash(
        degrees.value
    )
}

@Composable
fun Splash(
    degrees : Float
) {
    if (isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(
                    Color.Black
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
        }
    }else{
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            Color(0xFFf500B8)
                        ),
                    )
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo)
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreview() {
    Splash(1f)
}

@Preview
@Composable
fun SplashScreenDarkPreview() {
    Splash(1f)
}