package com.example.boruto_compose.ui.screen.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.boruto_compose.R
import com.example.boruto_compose.ui.theme.TOP_BAR_HEIGHT

@Composable
fun SearchTopBar(
    text: String,
    onValueChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {

    SearchWidget(
        text = text,
        onValueChange = onValueChange,
        onSearchClicked = onSearchClicked, onCloseClicked = onCloseClicked
    )

}

@Composable
fun SearchWidget(
    text: String,
    onValueChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Medium,
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.search_place_holder),
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium)
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {
                        onSearchClicked(text)
                    },
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_ninja),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.
                        semantics {
                            contentDescription = "CloseButton"
                        },
                    onClick = {
                        if (text.isNotEmpty()) {
                            onValueChange("")
                        } else {
                            onCloseClicked()
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .semantics {
                    contentDescription = "SearchTextField"
                }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview() {
    SearchTopBar(text = "", onValueChange = {}, onSearchClicked = {}) {

    }
}
