package com.example.oliveiraapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(
    onClickShoppingBox: () -> Unit = { }, modifier: Modifier,
    sizeProductsInCart: Int = 0,
    title: String
) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()

    ) {
        Row(
            modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            Text(
                text = title,
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

}
