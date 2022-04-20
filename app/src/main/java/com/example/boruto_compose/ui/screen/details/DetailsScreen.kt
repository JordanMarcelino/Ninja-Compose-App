package com.example.boruto_compose.ui.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.boruto_compose.ui.theme.spacing
import com.example.boruto_compose.util.Constant.BASE_URL
import com.example.boruto_compose.util.PaletteGenerator.extractColorFromBitmap
import com.example.boruto_compose.util.PaletteGenerator.parseImageUrlToBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val ninja by detailsViewModel.ninja
    val color by detailsViewModel.color
    val context = LocalContext.current

    if (color.isNotEmpty()) {
        DetailsBottomSheet(
            navController = navController,
            ninja = ninja,
            color = color
        )
    } else {
        detailsViewModel.onEvent(UiEvent.GenerateColorPalette)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = MaterialTheme.spacing.small
            )
        }
    }

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            if (event is UiEvent.GenerateColorPalette) {
                val bitmap = parseImageUrlToBitmap(
                    imageUrl = "$BASE_URL${ninja?.image}",
                    context = context
                )
                val palette = bitmap?.let { extractColorFromBitmap(bitmap = it) }
                palette?.let { detailsViewModel.setColorPalette(it) }
            }
        }
    }

}