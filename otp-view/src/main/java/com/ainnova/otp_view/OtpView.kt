package com.ainnova.otp_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpView(
    onFirstNumber: (String) -> Unit,
    onSecondNumber: (String) -> Unit,
    onThirdNumber: (String) -> Unit,
    onFourthNumber: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val (item1, item2, item3, item4) = FocusRequester.createRefs()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        OtpChar(
            modifier = Modifier
                .focusRequester(item1)
                .focusProperties {
                    next = item2
                    previous = item1
                },
            onOtpEntered = onFirstNumber
        )
        OtpChar(
            modifier = Modifier
                .focusRequester(item2)
                .focusProperties {
                    next = item3
                    previous = item1
                },
            onOtpEntered = onSecondNumber
        )
        OtpChar(
            modifier = Modifier
                .focusRequester(item3)
                .focusProperties {
                    next = item4
                    previous = item2
                },
            onOtpEntered = onThirdNumber
        )
        OtpChar(
            modifier = Modifier
                .focusRequester(item4)
                .focusProperties {
                    previous = item3
                    next = item4
                },
            onOtpEntered = onFourthNumber
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpChar(
    onOtpEntered: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pattern = remember { Regex("^[^\\t]*\$") } //to not accept the tab key as value
    var (text, setText) = remember { mutableStateOf("") }
    val maxChar = 1
    val focusManager = LocalFocusManager.current

    LaunchedEffect(
        key1 = text,
    ) {
        if (text.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxChar &&
                    ((it.isEmpty() || it.matches(pattern)))
                ) {
                    setText(it)
                    onOtpEntered(it)
                }

            },
            modifier = modifier
                .width(75.dp)
                .onKeyEvent {
                    if (it.key == Key.Tab) {
                        focusManager.moveFocus(FocusDirection.Next)
                        true
                    }
                    if (text.isEmpty() && it.key == Key.Backspace) {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    false
                },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Transparent,
                focusedIndicatorColor = Transparent,
                focusedContainerColor = Transparent,
                unfocusedContainerColor = Transparent
            ),

            )
        Divider(
            Modifier
                .width(50.dp)
                .padding(bottom = 2.dp)
                .offset(y = (-10).dp),
            color = Color.Red,
            thickness = 2.dp
        )
    }
}