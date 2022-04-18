package com.example.boruto_compose.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boruto_compose.R
import com.example.boruto_compose.ui.theme.STAR_SIZE
import com.example.boruto_compose.ui.theme.spacing
import com.example.boruto_compose.util.Constant.EMPTY_STAR
import com.example.boruto_compose.util.Constant.FILLED_STAR
import com.example.boruto_compose.util.Constant.HALF_FILLED_STAR

@Composable
fun RatingStar(
    modifier: Modifier = Modifier,
    rating: Double,
    scaleSize: Float = 3f
) {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }
    val starBounds = remember {
        starPath.getBounds()
    }

    val stars = calculateStar(rating = rating)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        stars[FILLED_STAR]?.let {
            repeat(it) {
                FilledStar(
                    starPath = starPath,
                    starBounds = starBounds,
                    scaleSize = scaleSize
                )
            }
        }

        stars[HALF_FILLED_STAR]?.let {
            repeat(it) {
                HalfFilledStar(
                    starPath = starPath,
                    starBounds = starBounds,
                    scaleSize = scaleSize
                )
            }
        }

        stars[EMPTY_STAR]?.let {
            repeat(it) {
                EmptyStar(
                    starPath = starPath,
                    starBounds = starBounds,
                    scaleSize = scaleSize
                )
            }
        }
    }

}

@Composable
fun FilledStar(
    starPath: Path,
    starBounds: Rect,
    scaleSize: Float
) {

    Canvas(
        modifier = Modifier
            .size(STAR_SIZE)
    ) {
        val left = (size.width / 2f) - (starBounds.width / 1.7f)
        val top = (size.height / 2f) - (starBounds.height / 1.7f)
        scale(
            scale = scaleSize
        )
        {
            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = Color(0xFFfb8500),
                )
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starBounds: Rect,
    scaleSize: Float
) {

    Canvas(
        modifier = Modifier
            .size(STAR_SIZE)
    ) {
        val left = (size.width / 2f) - (starBounds.width / 1.7f)
        val top = (size.height / 2f) - (starBounds.height / 1.7f)
        scale(
            scale = scaleSize
        )
        {
            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = Color.LightGray.copy(alpha = 0.5f),
                )
                clipPath(
                    path = starPath,
                ) {
                    drawRect(
                        color = Color(0xFFfb8500),
                        size = Size(
                            width = starBounds.maxDimension / 1.7f,
                            height = starBounds.maxDimension * scaleSize
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyStar(
    starPath: Path,
    starBounds: Rect,
    scaleSize: Float
) {

    Canvas(
        modifier = Modifier
            .size(STAR_SIZE)
    ) {
        val left = (size.width / 2f) - (starBounds.width / 1.7f)
        val top = (size.height / 2f) - (starBounds.height / 1.7f)
        scale(
            scale = scaleSize
        )
        {
            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = Color.LightGray.copy(alpha = 0.5f),
                )
            }
        }
    }
}

@Composable
fun calculateStar(
    rating: Double,
): Map<String, Int> {
    val maxStar by remember { mutableStateOf(5) }
    var filledStar by remember { mutableStateOf(0) }
    var halfFilledStar by remember { mutableStateOf(0) }
    var emptyStar by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating
            .toString()
            .split(".")
            .map { it.toInt() }

        if (firstNumber in 0..5 && lastNumber in 0..9) {
            filledStar = firstNumber

            if (lastNumber in 1..5) {
                halfFilledStar++
            }

            if (lastNumber in 6..9) {
                filledStar++
            }

            if (firstNumber == 5 && lastNumber > 0) {
                emptyStar = 5
                filledStar = 0
                halfFilledStar = 0
            }
        }
    }

    emptyStar = maxStar - (filledStar + halfFilledStar)

    return mapOf(
        FILLED_STAR to filledStar,
        HALF_FILLED_STAR to halfFilledStar,
        EMPTY_STAR to emptyStar
    )
}


@Preview(showBackground = true)
@Composable
fun FilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }
    val starBounds = remember {
        starPath.getBounds()
    }
    FilledStar(
        starPath = starPath,
        starBounds = starBounds,
        scaleSize = 2f
    )
}

@Preview(showBackground = true)
@Composable
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }
    val starBounds = remember {
        starPath.getBounds()
    }
    HalfFilledStar(
        starPath = starPath,
        starBounds = starBounds,
        scaleSize = 2f
    )
}

@Preview(showBackground = true)
@Composable
fun EmptyStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }
    val starBounds = remember {
        starPath.getBounds()
    }
    EmptyStar(
        starPath = starPath,
        starBounds = starBounds,
        scaleSize = 2f
    )
}