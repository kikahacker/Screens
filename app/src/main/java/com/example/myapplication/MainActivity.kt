package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.ui.screen.signIn.RecoverPassScreen
import com.example.myapplication.ui.screen.signIn.SignUpScreen
import com.example.myapplication.ui.screen.singIn.SingInScreen
import com.example.myapplication.ui.theme.MatuleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleTheme{
                SignUpScreen()
            }
        }
    }
}
