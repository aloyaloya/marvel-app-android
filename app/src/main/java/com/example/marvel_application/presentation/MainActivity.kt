package com.example.marvel_application.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.marvel_application.presentation.navigation.NavGraph
import com.example.marvel_application.presentation.ui.theme.InterTypography
import com.example.marvel_application.presentation.ui.theme.MarvelApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelApplicationTheme(dynamicColor = false) {
                MaterialTheme(typography = InterTypography) {
                    NavGraph()
                }
            }
        }
    }
}