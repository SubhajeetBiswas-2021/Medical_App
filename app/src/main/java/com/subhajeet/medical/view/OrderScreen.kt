package com.subhajeet.medical.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medical.viewModel.MyViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun OrderScreen(navController: NavController,userId: String,productId:String,viewModel: MyViewModel= hiltViewModel() ) {


    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    val getProductByIdstate = viewModel.getProductByIdstate.collectAsState()

    val addOrderDetailsstate = viewModel.addOrderDetailsstate.collectAsState()
    val Quantity =  remember { mutableStateOf("") }
    val message =  remember { mutableStateOf("") }
    val context = LocalContext.current


    var name = getProductByIdstate.value.success?.product?.name ?:""

    var price = getProductByIdstate.value.success?.product?.price ?:""


    var stock = getProductByIdstate.value.success?.product?.stock ?:""

    var product_id = getProductByIdstate.value.success?.product?.product_id ?:""

    val category = getProductByIdstate.value.success?.product?.category ?:""

    // ✅ Handle success or error toast
    LaunchedEffect(addOrderDetailsstate.value) {
        val state = addOrderDetailsstate.value
        if (state.success != null) {
            Toast.makeText(context, "Order placed successfully ✅", Toast.LENGTH_SHORT).show()
        }
        if (state.error != null) {
            Toast.makeText(context, "Failed: ${state.error}", Toast.LENGTH_SHORT).show()
        }
    }




        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp).padding(0.dp,30.dp,0.dp,0.dp)) {

            LazyColumn {

                item {
                  //  Text(text=name)
                  //  Text(text=price)
                   // Text(text=stock)
                   // Text(text=product_id)
                    TextField(value = "Name:${name}", onValueChange = {name=it}, readOnly = true, modifier = Modifier.fillMaxWidth())
                    Spacer(modifier=Modifier.height(10.dp))
                    TextField(value = "Price:${price}", onValueChange = {price=it}, readOnly = true,modifier = Modifier.fillMaxWidth())
                    Spacer(modifier=Modifier.height(10.dp))
                    TextField(value = "Stock:${stock}", onValueChange = {stock=it}, readOnly = true,modifier = Modifier.fillMaxWidth())
                    Spacer(modifier=Modifier.height(10.dp))
                    TextField(value = "ProductId:${product_id}", onValueChange = {product_id=it}, readOnly = true,modifier = Modifier.fillMaxWidth())
                    Spacer(modifier=Modifier.height(10.dp))
                    TextField(value = "Category:${category}", onValueChange = {product_id=it}, readOnly = true,modifier = Modifier.fillMaxWidth())
                    Spacer(modifier=Modifier.height(10.dp))
                    OutlinedTextField(
                        value = Quantity.value,
                        onValueChange = {
                            Quantity.value=it
                        },
                        label={Text(text = "Quantity")},
                        modifier=Modifier.fillMaxWidth()
                    )
                    Spacer(modifier=Modifier.height(10.dp))
                    OutlinedTextField(
                        value = message.value,
                        onValueChange = {
                            message.value=it
                        },
                        label={Text(text = "Message")},
                        modifier=Modifier.fillMaxWidth()
                    )

                    OutlinedButton(
                        onClick = { /*Handle place order action*/
                            when {
                                Quantity.value.isBlank() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter quantity",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                message.value.isBlank() -> {
                                    Toast.makeText(
                                        context,
                                        "Please enter a message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    viewModel.addOrderDetails(
                                        user_id = userId,
                                        productName = name,
                                        productId = product_id,
                                        Quantity = Quantity.value,
                                        price = price,
                                        message = message.value,
                                        category = category,
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text="Place Order")
                    }

                }
            }
        }




}

