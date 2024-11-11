package com.example.oliveiraapp.navigate

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.example.oliveiraapp.data.Product
import com.example.oliveiraapp.firebase.FireStoreRepository
import com.example.oliveiraapp.ui.camera.AnalyzeScreen
import com.example.oliveiraapp.ui.card.Card
import com.example.oliveiraapp.ui.details.ProductDetail
import com.example.oliveiraapp.ui.details.ProductDetailsViewModel
import com.example.oliveiraapp.ui.home.HomeScreen
import com.example.oliveiraapp.ui.menu.MenuScreen
import com.example.oliveiraapp.ui.registerproducts.DialogFormProductScreen
import com.example.oliveiraapp.ui.registerproducts.DialogFormProductViewModel



private const val ID = "id"
private const val HOME_ROUTE = "Home"
private const val CAMERA_ROUTE = "CAMERA"
private const val PRODUCT_DETAILS_ROUTE = "ProductDetails"
private const val CARD_ROUTE = "CARD"
private const val MENU_ROUTE = "MENU_ROUTE"
private const val FORM_PRODUCT = "FORM_PRODUCT"

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
        composable("$PRODUCT_DETAILS_ROUTE/{$ID}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(ID)
            val viewModel = hiltViewModel<ProductDetailsViewModel>()
            val state by viewModel.uiState.collectAsState()

            Log.e("Teste", "$productId")
            productId?.let { id ->
                viewModel.reloadProduct(id)
            }

            ProductDetail(
                state = state, onAddToCart = { _, _ -> },
            )
        }
        composable(CARD_ROUTE) {
            Card()
        }
        composable(MENU_ROUTE) {
            MenuScreen()
        }

        dialog(FORM_PRODUCT) {
            val viewModel = hiltViewModel<DialogFormProductViewModel>()
            val state by viewModel.uiState.collectAsState()

            DialogFormProductScreen(
                state = state,
                closeDialog = {
                    navControler.popBackStack()
                },
                onClickSaveProduct = {
                    viewModel.addProduct()
                    navControler.popBackStack()
                }

            )
        }
    }

}

fun NavHostController.navigateToDialogFormProduct() {
    navigate(FORM_PRODUCT)
}

fun NavHostController.navigateToCamera() {
    navigate(CAMERA_ROUTE)
}

fun NavHostController.navigatoToMenuScreen() {
    navigate(MENU_ROUTE)
}

fun NavHostController.navigateToProductDetails(id: String?) {
    this.navigate("$PRODUCT_DETAILS_ROUTE/$id")
}

fun NavHostController.navigateToCard() {
    navigate(CARD_ROUTE)
}

fun NavHostController.navigateToSearchProduct() {

}

fun NavHostController.navigateToRevenue() {

}
