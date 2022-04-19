package com.example.boruto_compose.ui.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.boruto_compose.R
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.ui.theme.NETWORK_ERROR_SIZE
import com.example.boruto_compose.ui.theme.spacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    ninjas: LazyPagingItems<Ninja>? = null
) {

    var message by remember { mutableStateOf("Find Your Favorite Ninjas!") }
    var icon by remember { mutableStateOf(R.drawable.search_document) }
    val alpha = remember { Animatable(initialValue = 0f) }
    val target = ContentAlpha.medium

    if (error != null) {
        message = parseStringError(error)
        icon = R.drawable.network_error
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = target,
            animationSpec = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            )
        )
    }

    EmptyContent(
        alpha = alpha.value,
        icon = icon,
        message = message,
        ninjas = ninjas,
        error = error
    )
}

@Composable
fun EmptyContent(
    alpha: Float,
    icon: Int,
    message: String,
    ninjas: LazyPagingItems<Ninja>? = null,
    error : LoadState.Error? = null,
    color: Color =
        if (isSystemInDarkTheme()) Color.DarkGray
        else Color.LightGray
) {
    var isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        modifier = Modifier.fillMaxSize(),
        onRefresh = {
            isRefreshing = true
            ninjas?.refresh()
            isRefreshing = false
        }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Error",
                modifier = Modifier
                    .size(NETWORK_ERROR_SIZE)
                    .alpha(alpha),
                tint = color
            )
            Text(
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.medium)
                    .alpha(alpha),
                text = message,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                textAlign = TextAlign.Center,
            )
        }
    }
}

fun parseStringError(
    error: LoadState.Error?
): String {
    return when (error?.error) {
        is SocketTimeoutException -> "Server Unavailable."
        is ConnectException -> "Internet Unavailable."
        else -> "Unknown Error"
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(
        alpha = 1f,
        icon = R.drawable.network_error,
        message = "Internet Unavailable",
    )
}