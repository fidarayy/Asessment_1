package com.mufidahrahman3138.assesment1mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.mufidahrahman3138.assesment1mobpro.ui.MainScreen
import com.mufidahrahman3138.assesment1mobpro.ui.SignUpScreen
import com.mufidahrahman3138.assesment1mobpro.ui.theme.Assesment1MobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesment1MobproTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    var isSignedUp by remember { mutableStateOf(false) }

    if (isSignedUp) {
        MainScreen()
    } else {
        SignUpScreen(onSignUpSuccess = { isSignedUp = true })
    }
}
