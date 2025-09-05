package com.subhajeet.medical.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medical.viewModel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacedOrders(navController: NavController, userId: String, viewModel: MyViewModel = hiltViewModel()) {

    val getOrderDetailsByIdstate = viewModel.getOrderDetailsByIdstate.collectAsState()

    LaunchedEffect(userId) {
        viewModel.getOrderDetailsById(userId)
    }
    // val dateOfOrder = getOrderDetailsByIdstate.value.success?.orderDetails?.date_of_order_creation
    //val isApproved = (getOrderDetailsByIdstate.value.success?.orderDetails?.isApproved ?:0).toInt()
    // val message = getOrderDetailsByIdstate.value.success?.orderDetails?.message
    // val productName = getOrderDetailsByIdstate.value.success?.orderDetails?.productName
    // val productId = getOrderDetailsByIdstate.value.success?.orderDetails?.product_id
    // val orderId = getOrderDetailsByIdstate.value.success?.orderDetails?.order_id
    // val category= getOrderDetailsByIdstate.value.success?.orderDetails?.category
    // val Quantity = getOrderDetailsByIdstate.value.success?.orderDetails?.Quantity

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Placed Orders") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,   // 🔵 Background color
                    titleContentColor = Color.White,       // 📝 Title color
                    navigationIconContentColor = Color.White, // ≡ Menu icon color
                    actionIconContentColor = Color.White
                )
            )
        }


    ) {innerPadding->

    when {
        getOrderDetailsByIdstate.value.isLoading -> {
            CircularProgressIndicator()
        }

        getOrderDetailsByIdstate.value.error != null -> {
            Text("Error: ${getOrderDetailsByIdstate.value.error}")
        }

        getOrderDetailsByIdstate.value.success?.orderDetails?.isNotEmpty() == true -> {
            LazyColumn(modifier = Modifier.padding(innerPadding).fillMaxSize() ) {
                items(getOrderDetailsByIdstate.value.success?.orderDetails ?: emptyList()) {
                    PlacedOrderCard(
                        productName = it.productName,
                        isApproved = it.isApproved.toString(),
                        Quantity = it.Quantity,
                        productId = it.product_id,
                        dateOfOrder = it.date_of_order_creation,
                        userId = it.user_id,
                        message = it.message
                    )

                }
            }
        }
    }
}

}


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlacedOrderCard(productName:String,isApproved:String,Quantity:String,productId:String,dateOfOrder:String,userId:String,message:String) {

    Card(modifier = Modifier.padding(8.dp).height(180.dp), colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    )) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,)
        {
          Box(modifier = Modifier.fillMaxWidth().height(120.dp) ) {

            Text(
                text = "Product:${productName}",
                fontSize = 16.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(6.dp, 0.dp, 6.dp, 0.dp),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Status:${isApproved}/Quantity:${Quantity}",
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "UserId:${userId}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "PrdctId:${productId}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )




        }
            Text(
                text = "Date:${dateOfOrder}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.Start)

                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Text(
                text = "Message:${message}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.End)

                    .padding(end = 12.dp, bottom = 8.dp)
            )
    }
    }

}