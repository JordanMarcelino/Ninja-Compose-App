package com.example.boruto_compose.ui.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
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
import com.example.boruto_compose.R
import com.example.boruto_compose.ui.theme.NETWORK_ERROR_SIZE
import com.example.boruto_compose.ui.theme.spacing
import timber.log.Timber

@Composable
fun EmptyScreen(
    error: LoadState.Error
) {
    Timber.d(
        error.toString()
    )
    val message by remember { mutableStateOf(parseStringError(message = error.toString())) }
    val icon by remember { mutableStateOf(R.drawable.network_error) }
    val alpha = remember { Animatable(initialValue = 0f) }
    val target = ContentAlpha.disabled

    LaunchedEffect(key1 = alpha) {
        alpha.animateTo(
            targetValue = target,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    }

    EmptyContent(
        alpha = alpha.value,
        icon = icon,
        message = message
    )
}

@Composable
fun EmptyContent(
    alpha: Float,
    icon: Int,
    message: String,
    color: Color =
        if (isSystemInDarkTheme()) Color.DarkGray
        else Color.LightGray
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
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

fun parseStringError(
    message: String
): String {
    return when {
        message.contains("SocketTimeoutException") -> "Server Unavailable"
        message.contains("ConnectException") -> "Internet Unavailable"
        else -> "Unknown Error"
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(
        alpha = 1f,
        icon = R.drawable.network_error,
        message = "Internet Unavailable"
    )
}