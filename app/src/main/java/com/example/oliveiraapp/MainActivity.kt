package com.example.oliveiraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.oliveiraapp.navigate.OliveiraNavHost
import com.example.oliveiraapp.ui.theme.OliveiraAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OliveiraAppTheme {
                val navCotroller = rememberNavController()
                OliveiraNavHost(navControler = navCotroller)
            }
        }
    }
}


