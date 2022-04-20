package com.example.boruto_compose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.boruto_compose.ui.theme.spacing

@Composable
fun OrderList(
    title : String,
    order : List<String>,
    textColor : Color
) {

    Column{
        Text(
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.small)
        )
        order.forEachIndexed { index, value ->
            Text(
                text = "${index + 1}. $value",
                color = textColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderListPreview() {
    OrderList(title = "Family", order = listOf(
        "Nagato",
        "Jiraiya"
    ), textColor = Color.Black)
}