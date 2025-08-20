package com.subhajeet.medical.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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


    val name = getProductByIdstate.value.success?.product?.name ?:""

    val price = getProductByIdstate.value.success?.product?.price ?:""


    val stock = getProductByIdstate.value.success?.product?.stock ?:""

    val product_id = getProductByIdstate.value.success?.product?.product_id ?:""




        Column(modifier = Modifier.fillMaxSize().padding(25.dp)) {

            LazyColumn {

                item {
                    Text(text=name)
                    Text(text=price)
                    Text(text=stock)
                    Text(text=product_id)


                }
            }
        }




}

