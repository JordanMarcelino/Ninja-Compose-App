package com.example.boruto_compose.ui.screen.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.boruto_compose.R
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.ui.component.InfoBox
import com.example.boruto_compose.ui.component.OrderList
import com.example.boruto_compose.ui.theme.BOTTOM_SHEET_HEIGHT
import com.example.boruto_compose.ui.theme.MIN_HEIGHT
import com.example.boruto_compose.ui.theme.spacing
import com.example.boruto_compose.util.Constant.BASE_URL
import com.example.boruto_compose.util.Constant.DARK_VIBRANT
import com.example.boruto_compose.util.Constant.ON_DARK_VIBRANT
import com.example.boruto_compose.util.Constant.VIBRANT
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalMaterialApi
@Composable
fun DetailsBottomSheet(
    navController: NavController,
    ninja: Ninja?,
    color: Map<String, String>
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#FFFFFF") }

    val systemUi = rememberSystemUiController()
    SideEffect {
        systemUi.setStatusBarColor(
            color = Color(parseColor(darkVibrant))
        )
    }

    LaunchedEffect(key1 = ninja) {
        vibrant = color[VIBRANT]!!
        darkVibrant = color[DARK_VIBRANT]!!
        onDarkVibrant = color[ON_DARK_VIBRANT]!!
    }

    val currentFraction = scaffoldState.currentFraction

    val radiusDp by animateDpAsState(
        targetValue =
        if (currentFraction == 1f) MaterialTheme.spacing.large
        else MaterialTheme.spacing.default,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusDp,
            topEnd = radiusDp
        ),
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            ninja?.let {
                BottomSheetContent(
                    ninja = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            ninja?.let { it1 ->
                BottomSheetBackground(
                    imageUrl = it1.image,
                    scale = currentFraction,
                    onCloseClicked = {
                        navController.popBackStack()
                    },
                    backgroundColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        sheetPeekHeight = BOTTOM_SHEET_HEIGHT,
        scaffoldState = scaffoldState
    )
}

@Composable
fun BottomSheetContent(
    ninja: Ninja,
    infoBoxIconColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(
                    id = R.string.logo
                ),
                modifier = Modifier
                    .size(MaterialTheme.spacing.large)
                    .weight(2f)
                    .padding(end = MaterialTheme.spacing.small),
                tint = contentColor
            )
            Text(
                text = ninja.name,
                color = contentColor,
                modifier = Modifier.weight(8f),
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoBox(
                smallText = stringResource(R.string.power),
                bigText = ninja.power.toString(),
                icon = painterResource(id = R.drawable.bolt),
                iconColor = infoBoxIconColor,
                textColor = contentColor
            )
            InfoBox(
                smallText = stringResource(R.string.month),
                bigText = ninja.month,
                icon = painterResource(id = R.drawable.calendar),
                iconColor = infoBoxIconColor,
                textColor = contentColor
            )
            InfoBox(
                smallText = stringResource(R.string.birthday),
                bigText = ninja.day,
                icon = painterResource(id = R.drawable.cake),
                iconColor = infoBoxIconColor,
                textColor = contentColor
            )
        }
        Text(
            text = stringResource(R.string.about),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.small),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            color = contentColor
        )
        Text(
            text = ninja.about,
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.medium)
                .alpha(ContentAlpha.medium),
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = contentColor,
            maxLines = 7
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderList(
                title = stringResource(R.string.family),
                order = ninja.family,
                textColor = contentColor
            )
            OrderList(
                title = stringResource(R.string.abilities),
                order = ninja.abilities,
                textColor = contentColor
            )
            OrderList(
                title = stringResource(R.string.nature_types),
                order = ninja.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@Composable
fun BottomSheetBackground(
    imageUrl: String,
    scale: Float,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit
) {

    val url = "$BASE_URL$imageUrl"
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest
                .Builder(
                    context
                )
                .data(url)
                .error(R.drawable.ic_placeholder)
                .build(),
            contentDescription = stringResource(id = R.string.ninja_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(scale + MIN_HEIGHT)
                .align(Alignment.TopStart)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                onClick = onCloseClicked,
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close),
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .size(MaterialTheme.spacing.large),
                    tint = backgroundColor
                )
            }
        }
    }

}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val current = bottomSheetState.currentValue
        val target = bottomSheetState.targetValue

        return when {
            current == BottomSheetValue.Expanded && target == BottomSheetValue.Expanded -> 0f
            current == BottomSheetValue.Collapsed && target == BottomSheetValue.Collapsed -> 1f
            current == BottomSheetValue.Collapsed && target == BottomSheetValue.Expanded -> 1f - fraction
            current == BottomSheetValue.Expanded && target == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    DetailsBottomSheet(
        navController = rememberNavController(),
        ninja = Ninja(
            id = 10,
            name = "Isshiki",
            image = "/static/ishiki.jpg",
            about = "A thousand years ago, Isshiki came to Earth alongside Kaguya with the objective to plant a Tree to harvest its Chakra Fruit. While Kaguya, being lower-ranked, was planned to be sacrificed to create the Chakra Fruit, she instead turned on Isshiki, leaving him on the verge of death after destroying Isshiki's lower half. Encountering Jigen and not having the strength to implant a Kāma on him, Isshiki devised a desperate plan and shrunk himself to enter the monk's ear in order to survive his injury by absorbing Jigen's nutrients.",
            rating = 5.0,
            power = 100,
            month = "Jan",
            day = "1st",
            family = listOf(
                "Otsutsuki Clan"
            ),
            abilities = listOf(
                "Sukunahikona",
                "Daikokuten",
                "Byakugan",
                "Space–Time",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Fire"
            )
        ),
        mapOf()
    )
}
