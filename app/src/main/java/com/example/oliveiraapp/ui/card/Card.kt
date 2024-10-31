package com.example.oliveiraapp.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oliveiraapp.ui.components.ProductCard
import com.example.oliveiraapp.ui.home.TopAppBarOliveiraTask
import com.example.oliveiraapp.ui.theme.ComponentsGreen
import com.example.oliveiraapp.ui.theme.OliveiraAppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CardPrev() {
    Card()
}

@Composable
fun Card(
    totalPurchase: Double = 0.0,
    onBack: () -> Unit = {}
) {

    Column {
        TopAppBarOliveiraTask(
            name = "Carrinho",
            showBackArrow = true,
            modifier = Modifier,
            onClickBack = onBack
        )
        ProductCard(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .padding(vertical = 10.dp, horizontal = 8.dp)
        )

        Column(modifier = Modifier.padding(start =10.dp, end = 10.dp)) {
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.onSecondaryContainer)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "TOTAL", color = MaterialTheme.colorScheme.secondaryContainer,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }

                val roundedPrice = String.format("R$ %.2f", totalPurchase)
                Text(
                    text = roundedPrice,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ComponentsGreen
                )
            ) {
                Text(
                    text = "Finalizar compra",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

