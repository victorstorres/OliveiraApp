package com.example.oliveiraapp.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.oliveiraapp.data.Product
import com.example.oliveiraapp.ui.camera.AnalyzeScreen
import com.example.oliveiraapp.ui.card.Card
import com.example.oliveiraapp.ui.details.ProductDetail
import com.example.oliveiraapp.ui.home.HomeScreen


private const val HOME_ROUTE = "Home"
private const val CAMERA_ROUTE = "CAMERA"
private const val PRODUCT_DETAILS_ROUTE = "ProductDetails"
private const val CARD_ROUTE = "CARD"

@Composable
fun OliveiraNavHost(
    navControler: NavHostController
) {

    NavHost(navController = navControler, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            HomeScreen(navControler)
        }
        composable(CAMERA_ROUTE) {
            AnalyzeScreen(onBack = { navControler.popBackStack() })
        }
        composable(PRODUCT_DETAILS_ROUTE) {
            ProductDetail(
                product = Product(""), onAddToCart = { _, _ -> },
                onBack = { navControler.popBackStack() }
            )
        }

        composable(CARD_ROUTE) {
            Card(onBack = { navControler.popBackStack() })
        }
    }

}


fun NavHostController.navigateToCamera() {
    navigate(CAMERA_ROUTE)
}

fun NavHostController.navigateToProductDetails(product: Product) {
    navigate(PRODUCT_DETAILS_ROUTE)
}

fun NavHostController.navigateToCard() {
    navigate(CARD_ROUTE)
}

