package com.example.oliveiraapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.oliveiraapp.R
import com.example.oliveiraapp.data.Product
import com.example.oliveiraapp.navigate.navigateToCamera
import com.example.oliveiraapp.navigate.navigateToCard
import com.example.oliveiraapp.navigate.navigateToProductDetails
import com.example.oliveiraapp.ui.components.ProductCard
import com.example.oliveiraapp.ui.theme.ComponentsGreen


@Composable
fun HomeScreen(
    navController: NavHostController

) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()


    val showSheetPeekHeight = if (state.showPreview) 40.dp else 0.dp
    Column {
        TopAppBarOliveiraTask(
            name = "Oliveira App",
            showCard = true,
            modifier = Modifier,
            onClickShoppingBox = {
                navController.navigateToCard()
            })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0XFFEDF3ED))
        ) {
            ProductCard(
                modifier = Modifier
                    .clickable { navController.navigateToProductDetails(Product()) }
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            )


            FloatingActionButton(
                onClick = {
                    navController.navigateToCamera()
                    viewModel.setShowPreview(!state.showPreview)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = ComponentsGreen,
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_camera),
                    contentDescription = "HomeScreenFloatActionButton",
                    tint = Color.White
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarOliveiraTask(
    name: String = "",
    showCard: Boolean = false,
    showBackArrow: Boolean = false,
    onClickShoppingBox: () -> Unit = { }, modifier: Modifier,
    onClickBack: () -> Unit = { },
    sizeProductsInCart: Int = 0,

    ) {
    var lastClickTimeBack: Long = 0
    var lastClickTimeCard: Long = 0

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ComponentsGreen
        ), title = {

            if (showBackArrow) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp, start = 4.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Back",
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            val currentTime = System.currentTimeMillis()
                            if(currentTime - lastClickTimeBack > 2000){
                                onClickBack()
                            }
                            lastClickTimeBack = currentTime
                            }
                        .padding(start = 12.dp, top = 8.dp, bottom = 8.dp),
                ) {
                    Spacer(modifier = Modifier.size(24.dp))
                }

            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier

                )
            }

            if (showCard) {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp)

                ) {


                    BadgedBox(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        badge = {
                            Badge(
                                modifier = Modifier.offset(y = (-8).dp),
                                content = {
                                    Text(sizeProductsInCart.toString())
                                },
                                containerColor = Color.Black,
                                contentColor = Color.White
                            )
                        }) {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Shopping Cart",
                            modifier = Modifier
                                .offset(x = 8.dp),
                            tint = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                val currentTime = System.currentTimeMillis()
                                if(currentTime - lastClickTimeBack > 1000){
                                    onClickShoppingBox()
                                }
                                lastClickTimeBack = currentTime
                            }
                            .padding(end = 16.dp, top = 8.dp, bottom = 8.dp),
                    ) {
                        Spacer(modifier = Modifier.size(24.dp))
                    }
                }
            }


        })
}


@Preview
@Composable
private fun HomeScreenPrev() {
    val context = LocalContext.current
    HomeScreen(NavHostController(context))
}


