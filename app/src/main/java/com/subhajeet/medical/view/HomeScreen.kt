package com.subhajeet.medical.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

Column(modifier = Modifier.fillMaxSize()) {
    Text(text="It is the Home Screen")
}

}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun eachCard() {

    Card(modifier = Modifier.padding(5.dp,45.dp,5.dp,5.dp).height(140.dp), colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    )) {
        Box(modifier = Modifier.fillMaxSize()){

            Text(text ="name", fontSize = 20.sp, textAlign = TextAlign.Right, modifier = Modifier.padding(6.dp,0.dp,6.dp,0.dp), fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold )

            Spacer(modifier=Modifier.height(10.dp))

            Text(
                text = "price",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Spacer(modifier=Modifier.height(10.dp))

            Text(
                text = "stock",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp, bottom = 8.dp)
            )

            Spacer(modifier=Modifier.height(10.dp))

            Text(
                text = "product_id",
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