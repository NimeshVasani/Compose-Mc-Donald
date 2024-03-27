package com.example.composemcdonald.ui.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.composemcdonald.R
import com.example.composemcdonald.ui.theme.McComposeTheme

@ExperimentalAnimationApi
@Composable
fun LoginScreen(
    onButtonClick: () -> Unit
) {
    Scaffold(contentWindowInsets = WindowInsets.safeContent) { padding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image covering the entire screen with shadow
            Image(
                painter = painterResource(id = R.drawable.login_back_2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Content overlay containing login and sign up buttons
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Empty spacer to push buttons to the bottom of the screen
                Spacer(modifier = Modifier.weight(1f))

                // Login button
                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(0.5f)
                        .height(70.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp,Color.Yellow),
                    colors = ButtonDefaults.buttonColors(Color(0X50000000))
                ) {
                    Text(text = "Login", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign up button
                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(0.5f)
                        .height(70.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                ) {
                    Text(text = "Sign Up", fontSize = 20.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun LoginScreenPreview() {
    McComposeTheme {
        LoginScreen(
            onButtonClick = {}
        )
    }
}