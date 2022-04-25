package com.example.boruto_compose.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.boruto_compose.R
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.ui.component.RatingStar
import com.example.boruto_compose.ui.component.ShimmerEffect
import com.example.boruto_compose.ui.navigation.Screen
import com.example.boruto_compose.ui.theme.NINJA_ITEM_SIZE
import com.example.boruto_compose.ui.theme.spacing
import com.example.boruto_compose.util.Constant.BASE_URL

@Composable
fun NinjaList(
    navController: NavController,
    ninjas: LazyPagingItems<Ninja>
) {
    val shouldShowItem = shouldShowItem(ninjas = ninjas)

    if (shouldShowItem) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(
                items = ninjas,
                key = { ninja ->
                    ninja.id
                }
            ) { ninja ->
                ninja?.let {
                    NinjaItem(
                        ninja = it,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun shouldShowItem(
    ninjas: LazyPagingItems<Ninja>
): Boolean {
    ninjas.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, ninjas = ninjas)
                false
            }
            ninjas.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@Composable
fun NinjaItem(
    ninja: Ninja,
    navController: NavController
) {
    val url = "$BASE_URL${ninja.image}"
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(NINJA_ITEM_SIZE)
            .clickable {
                navController.navigate(Screen.Details.passNinjaId(ninja.id))
            }
            .semantics {
                contentDescription = "NinjaItem"
            },
        contentAlignment = Alignment.BottomStart
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest
                .Builder(context = context)
                .crossfade(1000)
                .data(url)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .build(),
            contentDescription = stringResource(R.string.ninja_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(MaterialTheme.spacing.large))
        )
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.45f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomEnd = MaterialTheme.spacing.large,
                bottomStart = MaterialTheme.spacing.large
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                Text(
                    text = ninja.name,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color.White
                )
                Text(
                    text = ninja.about,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    color = Color.White.copy(ContentAlpha.medium)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    RatingStar(
                        modifier = Modifier.padding(MaterialTheme.spacing.small),
                        rating = ninja.rating
                    )
                    Text(
                        text = "(${ninja.rating})",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Medium,
                        color = Color.White.copy(ContentAlpha.medium)
                    )
                }
            }
        }
    }

}