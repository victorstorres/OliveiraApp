package com.example.oliveiraapp.ui.registerproducts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.oliveiraapp.R
import com.example.oliveiraapp.ui.theme.ComponentsGreen

@Composable
fun DialogFormProductScreen(
    onClickCamera: () -> Unit = {},
    onClickSaveProduct: () -> Unit = {},
    closeDialog: () -> Unit = {},
    state: DialogFormProductUiState = DialogFormProductUiState(),
    imageGallery: String = "",
) {
    Dialog(
        onDismissRequest = closeDialog, content = {
            Column(
                modifier =
                Modifier
                    .clip(RoundedCornerShape(8))
                    .heightIn(max = 600.dp)
                    .background(Color.White)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(modifier = Modifier.align(Alignment.End)) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "CloseDialog",
                        modifier = Modifier.clickable {
                            closeDialog()
                        })
                }

                Text(
                    text = "Cadastre o seu Produto", fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(5, 5)),
                    model = imageGallery,
                    placeholder = painterResource(R.drawable.no_image),
                    error = painterResource(R.drawable.no_image),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Photo Product",
                )


                OutlinedTextField(
                    leadingIcon = {
                        IconButton(
                            onClick = onClickCamera,
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.ic_add_image_product
                                ),
                                modifier = Modifier.size(30.dp),
                                tint = Color.Black,
                                contentDescription = "icon_gallery"
                            )
                        }
                    },
                    value = state.nameProduct,
                    onValueChange = state.onNameChange,
                    label = {
                        Text(
                            text = "Nome do Produto",
                        )
                    })

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = state.priceProduct,
                    onValueChange =  state.onPriceChange ,
                    label = {
                        Text("Preço")
                    })
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    value = state.quantifyProduct,
                    onValueChange = state.onQuantifyChange ,
                    label = {
                        Text("Quantidade")
                    })
                Spacer(modifier = Modifier)
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ComponentsGreen
                    ),
                    onClick = onClickSaveProduct
                ) {
                    Text("Salvar Produto")
                }

            }

        })
}


@Preview(showBackground = true)
@Composable
private fun RegisterProductScreen() {
    DialogFormProductScreen()
}