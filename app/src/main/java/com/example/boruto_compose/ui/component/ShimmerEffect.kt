package com.example.boruto_compose.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.boruto_compose.ui.theme.NINJA_ITEM_SIZE
import com.example.boruto_compose.ui.theme.spacing

@Composable
fun ShimmerEffect() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    ) {
        items(2) {
            AnimatedShimmer()
        }
    }
}

@Composable
fun AnimatedShimmer() {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 1f, targetValue = 0f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    ShimmerItem(alpha = alpha)
}

@Composable
fun ShimmerItem(
    alpha: Float,
    shimmerColor: Color =
        if (isSystemInDarkTheme()) Color.DarkGray.copy(
            alpha = ContentAlpha.medium
        )
        else Color.LightGray
) {
    Surface(
        modifier = Modifier
            .size(NINJA_ITEM_SIZE)
            .padding(MaterialTheme.spacing.small),
        shape = RoundedCornerShape(MaterialTheme.spacing.large),
        color =
        if (isSystemInDarkTheme()) Color.Black
        else Color.LightGray.copy(
            alpha = ContentAlpha.medium
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .padding(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(MaterialTheme.spacing.large)
                    .alpha(alpha),
                color = shimmerColor
            ) {}
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            repeat(3) {
                Surface(
                    shape = RoundedCornerShape(MaterialTheme.spacing.small),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.spacing.medium)
                        .alpha(alpha),
                    color = shimmerColor
                ) {}
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                repeat(5) {
                    Surface(
                        shape = RoundedCornerShape(MaterialTheme.spacing.large),
                        modifier = Modifier
                            .size(MaterialTheme.spacing.medium)
                            .alpha(alpha),
                        color = shimmerColor
                    ) {}
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShimmerItemPreview() {
    AnimatedShimmer()
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerItemDarkPreview() {
    AnimatedShimmer()
}

