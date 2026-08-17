package com.example.p2plending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import com.example.p2plending.data.MockData
import com.example.p2plending.data.User
import com.example.p2plending.ui.HomeScreen
import com.example.p2plending.ui.LoginScreen
import com.example.p2plending.ui.theme.P2PLendingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P2PLendingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
private fun AppNavigation() {
    // Simple two-screen flow: true once the user has signed in.
    var loggedIn by rememberSaveable { mutableStateOf(false) }
    var user by remember { mutableStateOf<User?>(null) }

    AnimatedContent(
        targetState = loggedIn,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "screen"
    ) { isLoggedIn ->
        if (isLoggedIn) {
            HomeScreen(
                user = user ?: MockData.demoUser,
                onLogout = {
                    user = null
                    loggedIn = false
                }
            )
        } else {
            LoginScreen(
                onLoginSuccess = { loggedInUser ->
                    user = loggedInUser
                    loggedIn = true
                }
            )
        }
    }
}
