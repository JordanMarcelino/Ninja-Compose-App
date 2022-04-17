package com.example.boruto_compose.ui.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boruto_compose.R

@Composable
fun HomeTopBar(
    onSearchClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        title = {
            Text(
                text = stringResource(R.string.explore),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        actions = {
            IconButton(
                onClick = onSearchClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar {

    }
}