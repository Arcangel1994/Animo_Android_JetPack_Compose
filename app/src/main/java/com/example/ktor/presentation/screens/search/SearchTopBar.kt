package com.example.ktor.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.ktor.R
import com.example.ktor.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.ktor.ui.theme.topAppBarBackgroundColor
import com.example.ktor.ui.theme.topAppBarContentColor

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
){
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}

@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {onTextChange(it)},
            placeholder = {
                Text(
                    text = "Search hero...",
                    color = Color.White,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon), tint = MaterialTheme.colors.topAppBarContentColor)
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                              if(text.isNotEmpty()){
                                onTextChange("")
                              }else{
                                  onCloseClicked()
                              }
                    },
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = stringResource(R.string.close_icon), tint = MaterialTheme.colors.topAppBarContentColor)
                }
            },
           keyboardOptions = KeyboardOptions(
               imeAction = ImeAction.Search
           ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor
            )
        )
    }

}

@Preview
@Composable
fun SearchWidgetPreview() {
    SearchWidget(text = "", onTextChange = {}, onSearchClicked = {}, onCloseClicked = {})
}