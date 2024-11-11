package com.example.oliveiraapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.oliveiraapp.navigate.navigateToDialogFormProduct
import com.example.oliveiraapp.navigate.navigateToProductDetails
import com.example.oliveiraapp.navigate.navigateToRevenue
import com.example.oliveiraapp.navigate.navigateToSearchProduct
import com.example.oliveiraapp.ui.components.ProductCard
import com.example.oliveiraapp.ui.theme.ComponentsGreen
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    navController: NavHostController

) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.fillMaxSize(),
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.6f),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                                .background(Color.LightGray)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Profile Icon",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Nome", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Email", fontWeight = FontWeight.Light, fontSize = 14.sp)
                        }
                    }


                    Spacer(modifier = Modifier.padding(20.dp))

                    Box(modifier = Modifier.clickable { navController.navigateToSearchProduct() }) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Search, contentDescription = "SearchProduct")
                            Text(
                                text = "Procurar Produto",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp

                            )

                        }
                    }

                    Box(modifier = Modifier.clickable { navController.navigateToCamera() }) {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "OpenCamera"
                            )
                            Text(
                                text = "Câmera",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }
                    Box(modifier = Modifier.clickable { navController.navigateToDialogFormProduct() }) {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add_product),
                                contentDescription = "SearchProduct"
                            )
                            Text(
                                text = "Adicionar Produto",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }


                    Box(modifier = Modifier.clickable { navController.navigateToCard() }) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "OpenCard")
                            Text(
                                text = "Carrinho",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }
                    Box(modifier = Modifier.clickable { navController.navigateToRevenue() }) {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_revenue_24),
                                contentDescription = "SearchProduct"
                            )
                            Text(
                                text = "Faturamento",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }

                }


            }

        }) {
        val coroutine = rememberCoroutineScope()
        Column {
            TopAppBarOliveiraTask(
                name = "Oliveira App",
                showCard = true,
                showMenu = true,
                modifier = Modifier,
                onClickMenu = {
                    coroutine.launch {
                        if (drawerState.isOpen) drawerState.close() else drawerState.open()
                    }
                },
                onClickShoppingBox = {
                    navController.navigateToCard()
                })

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0XFFEDF3ED))
            ) {
                ProductCard(
                    state = state,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    onClickProduct = {
                        navController.navigateToProductDetails(Product(state.nameProduct))

                    }
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
    showMenu: Boolean = false,
    onClickShoppingBox: () -> Unit = { }, modifier: Modifier,
    onClickMenu: () -> Unit = { },
    sizeProductsInCart: Int = 0,

    ) {
    var lastClickTimeCard: Long = 0
    var lastClickTimeMenu: Long = 0

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ComponentsGreen
        ), title = {

            if (showMenu) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp, start = 7.dp)
                ) {
                    Icon(
                        Icons.Default.Menu,
                        tint = Color.White,
                        contentDescription = "Menu",
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            val currentTime = System.currentTimeMillis()
                            if (currentTime - lastClickTimeMenu > 1000) {
                                onClickMenu()
                            }
                            lastClickTimeMenu = currentTime
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
                                if (currentTime - lastClickTimeCard > 1000) {
                                    onClickShoppingBox()
                                }
                                lastClickTimeCard = currentTime
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


