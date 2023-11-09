package com.example.otpview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ainnova.otp_view.OtpView
import com.example.otpview.ui.theme.OtpViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}

@Composable
fun MainView(){
    OtpViewTheme {
        // A surface container using the 'background' color from the theme
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            OtpView(
                onFirstNumber = {},
                onSecondNumber = {},
                onThirdNumber = {},
                onFourthNumber = {}
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun MainPreview(){
   MainView()
}
