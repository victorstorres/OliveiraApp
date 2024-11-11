package com.example.oliveiraapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oliveiraapp.R
import com.example.oliveiraapp.data.Product
import com.example.oliveiraapp.ui.home.HomeUiState
import com.example.oliveiraapp.ui.theme.PriceGreen

@Composable
fun ProductCard(
    state: HomeUiState = HomeUiState(),
    onClickProduct: (String?) -> Unit  = {  },
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        items(state.listProducts) { product ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClickProduct(product.id)
                    }
                    .padding(vertical = 5.dp),

                ) {
                Text(
                    text = product.quantify.toString(),
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(start = 5.dp, end = 5.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                ) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Spacer(modifier = Modifier.size(10.dp))
                        val fontWeight: FontWeight = FontWeight.SemiBold

                        Text(product.name, style = TextStyle(fontWeight = fontWeight))
                        Text(
                            text = "R$ ${product.price}",
                            style = TextStyle(
                                color = PriceGreen,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                    }
                    Image(
                        modifier = Modifier.fillMaxHeight(0.5f),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "ImageProduct"
                    )

                }
            }
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CardStorePrev() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductCard(
            state = HomeUiState(
                listProducts = listOf(
                    Product(
                        "Nome",
                        price = 10.2,
                        quantify = 10
                    )
                )
            )
        )

    }
}