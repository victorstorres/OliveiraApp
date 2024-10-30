package com.example.oliveiraapp.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.oliveiraapp.ui.camera.AnalyzeScreen
import com.example.oliveiraapp.ui.home.HomeScreen


private val HOME_ROUTE = "Home"
private val CAMERA_ROUTE = "CAMERA"

@Composable
fun OliveiraNavHost(
    navControler: NavHostController
) {

    NavHost(navController = navControler, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            HomeScreen(navControler)
        }
        composable(CAMERA_ROUTE) {
            AnalyzeScreen()
        }
    }

}


fun NavHostController.navigateToCamera(){
    navigate(CAMERA_ROUTE)
}