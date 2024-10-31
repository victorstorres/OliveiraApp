package com.example.oliveiraapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import com.example.oliveiraapp.R
import com.example.oliveiraapp.ui.theme.PriceGreen

@Composable
fun ProductCard(modifier: Modifier = Modifier) {


    LazyColumn(modifier = modifier) {
        items(10) {
            ElevatedCard(
                shape = RectangleShape,
                elevation = androidx.compose.material3.CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.17f)
            ) {
                Card(
                    modifier = Modifier.fillMaxSize().padding(vertical = 5.dp),
                    shape = RectangleShape,

                    ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .border(3.dp, Color.Black)
                            .padding(5.dp)
                    ) {

                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Spacer(modifier = Modifier.size(10.dp))
                            val fontWeight: FontWeight = FontWeight.SemiBold

                            Text("Nome: Produto", style = TextStyle(fontWeight = fontWeight))
                            Text(
                                "Preço: Produto",
                                style = TextStyle(
                                    color = PriceGreen,
                                    fontWeight = FontWeight.SemiBold
                                ),
                            )
                            Text(
                                "Quantidade: Produto",
                                style = TextStyle(fontWeight = FontWeight.Light)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "ImageProduct"
                        )

                    }
                }
            }
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CardStorePrev() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductCard()

    }
}