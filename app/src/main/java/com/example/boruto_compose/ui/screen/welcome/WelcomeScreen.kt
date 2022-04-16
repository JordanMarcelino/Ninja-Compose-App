package com.example.boruto_compose.ui.screen.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.boruto_compose.R
import com.example.boruto_compose.ui.navigation.Screen
import com.example.boruto_compose.ui.theme.spacing
import com.example.boruto_compose.util.Constant.ON_BOARDING_COUNT
import com.example.boruto_compose.util.Constant.ON_BOARDING_LAST_PAGE
import com.google.accompanist.pager.*

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(
            count = ON_BOARDING_COUNT,
            modifier = Modifier
                .weight(10f),
            verticalAlignment = Alignment.Top,
            state = pagerState
        ) { page ->
            PagerScreen(onBoardingPage = pages[page])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .weight(1f)
                .align(CenterHorizontally),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            indicatorWidth = MaterialTheme.spacing.medium,
            spacing = MaterialTheme.spacing.small
        )
        FinishButton(pagerState = pagerState) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.setComplete(true)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = onBoardingPage.title
        )
        Text(
            text = onBoardingPage.title,
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = onBoardingPage.description,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.large)
                .padding(top = MaterialTheme.spacing.medium),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )
    }
}

@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(MaterialTheme.spacing.large),
        verticalAlignment = Alignment.Top,
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage == ON_BOARDING_LAST_PAGE,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
                shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                colors = buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = stringResource(R.string.finish),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun FirstPagePreview() {
    PagerScreen(onBoardingPage = pages[0])
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun SecondPagePreview() {
    PagerScreen(onBoardingPage = pages[1])
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun ThirdPagePreview() {
    PagerScreen(onBoardingPage = pages[2])
}