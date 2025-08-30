package com.subhajeet.medical.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.subhajeet.medical.viewModel.MyViewModel

@Composable
fun OrderScreen(navController: NavController,productId:String,viewModel: MyViewModel= hiltViewModel() ) {


    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    val getProductByIdstate = viewModel.getProductByIdstate.collectAsState()


    var name = getProductByIdstate.value.success?.product?.name ?:""

    var price = getProductByIdstate.value.success?.product?.price ?:""


    var stock = getProductByIdstate.value.success?.product?.stock ?:""

    var product_id = getProductByIdstate.value.success?.product?.product_id ?:""




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

                  /*  OutlinedButton(
                        onClick = { /*Handle login action*/
                            viewModel?.login(email =email.value,
                                password = password.value)},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text="Login")
                    }*/

                }
            }
        }




}

