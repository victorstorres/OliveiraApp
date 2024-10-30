package com.example.oliveiraapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.oliveiraapp.R
import com.example.oliveiraapp.ui.camera.AnalyzeScreen
import com.example.oliveiraapp.ui.theme.BlueOliveira
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(

) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()

    val showSheetPeekHeight = if (state.showPreview) 40.dp else 0.dp
    val coroutine = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = {
            TopAppBarOliveiraTask(
                modifier = Modifier,
                onClickShoppingBox = {
                    Toast.makeText(context, "Shopping Cart", Toast.LENGTH_SHORT).show()
                })
        },
        sheetContent = {
            Text("Estamos fazendo testes")
        },
        sheetPeekHeight = showSheetPeekHeight,
        sheetShadowElevation = 1.dp,
        sheetTonalElevation = 8.dp,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FloatingActionButton(
                onClick = {
                    coroutine.launch {
                        openCamera
                    }
                    viewModel.setShowPreview(!state.showPreview)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = BlueOliveira,
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
private fun TopAppBarOliveiraTask(
    onClickShoppingBox: () -> Unit = { }, modifier: Modifier,
    sizeProductsInCart: Int = 0,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BlueOliveira
        ), title = {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp)

            ) {
                Row(
                    modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Text(
                        text = "Oliveira App",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier

                    )
                }
                BadgedBox(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    badge = {
                        Badge(
                            modifier = Modifier.offset(y = (-8).dp),
                            content = {
                                Text(sizeProductsInCart.toString())
                            },
                            containerColor = MaterialTheme.colorScheme.onBackground,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    }) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        modifier = Modifier
                            .offset(x = 8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onClickShoppingBox() }
                        .padding(end = 16.dp, top = 8.dp, bottom = 8.dp),
                ) {
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }

        })
}


@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}


val openCamera = @Composable {
    AnalyzeScreen()
}