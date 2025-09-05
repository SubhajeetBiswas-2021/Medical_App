package com.subhajeet.medical.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.subhajeet.medical.view.nav.Routes
import com.subhajeet.medical.viewModel.MyViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, userId: String, viewModel:MyViewModel= hiltViewModel()) {



    val getAllProductstate = viewModel.getAllProductstate.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.getAllProduct()
    }
    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Available Products") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,   // 🔵 Background color
                    titleContentColor = Color.White,       // 📝 Title color
                    navigationIconContentColor = Color.White, // ≡ Menu icon color
                    actionIconContentColor = Color.White
                )
            )
        }


    ){innerPadding ->

    when{
        getAllProductstate.value.isLoading ->{
            CircularProgressIndicator()
        }
        getAllProductstate.value.error != null ->{
           Text("Error: ${getAllProductstate.value.error}")
        }

        getAllProductstate.value.success?.products?.isNotEmpty() == true ->{
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(getAllProductstate.value.success?.products?: emptyList()){
                    eachCard(
                        name = it.name,
                        price = it.price,
                        stock = it.stock,
                        product_id = it.product_id,
                        onClick = {
                            navController.navigate(Routes.OrderRoutes(
                                productId = it.product_id,
                                 userId=userId
                            ))
                        }
                    )

                }
            }
        }
    }
}
}




//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun eachCard(name:String,price:String,stock:String,product_id:String,onClick:()->Unit) {

    Card(modifier = Modifier.padding(8.dp).height(140.dp).clickable {
        onClick()
    }, colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    )) {
        Box(modifier = Modifier.fillMaxSize()){

            Text(text =name, fontSize = 20.sp, textAlign = TextAlign.Right, modifier = Modifier.padding(6.dp,0.dp,6.dp,0.dp), fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold )

            Spacer(modifier=Modifier.height(10.dp))

            Text(
                text = "Price:${price}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Spacer(modifier=Modifier.height(10.dp))

            Text(
                text = "Stock:${stock}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Spacer(modifier=Modifier.height(10.dp))

            Text(
                text = product_id,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )



        }
    }

}