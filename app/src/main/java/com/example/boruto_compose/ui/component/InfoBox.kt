package com.example.boruto_compose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.boruto_compose.R
import com.example.boruto_compose.ui.theme.spacing

@Composable
fun InfoBox(
    smallText: String,
    bigText: String,
    icon: Painter,
    iconColor: Color,
    textColor: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = smallText,
            tint = iconColor,
            modifier = Modifier
                .padding(end = MaterialTheme.spacing.small)
                .size(MaterialTheme.spacing.large)
        )
        Column{
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview() {
    InfoBox(
        smallText = "Power",
        bigText = "92",
        icon = painterResource(
            id = R.drawable.bolt
        ),
        iconColor = MaterialTheme.colorScheme.primary,
        textColor = MaterialTheme.colorScheme.onBackground
    )
}