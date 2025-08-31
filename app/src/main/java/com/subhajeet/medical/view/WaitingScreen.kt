package com.subhajeet.medical.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medical.view.nav.Routes
import com.subhajeet.medical.viewModel.MyViewModel


@Composable
fun WaitingScreen(navController: NavController?, userId: String,viewModel: MyViewModel?= hiltViewModel()) {


    val getUserByIdstate = viewModel?.getUserByIdstate?.collectAsState()



    LaunchedEffect(userId) {
        if (viewModel != null) {
            viewModel.getUserById(userId)
        }
    }

    val isApproved = getUserByIdstate?.value?.success?.user?.isApproved == 1
    val block = getUserByIdstate?.value?.success?.user?.block == 0
    LaunchedEffect(isApproved) {
        if (isApproved && block) {
            navController?.navigate(Routes.HomeRoutes(userId)) {

               /* popUpTo(Routes.WaitingRoutes) {
                    inclusive = true
                } //We call popUpTo so that pressing back doesn’t return to the WaitingScreen.*/

            }

        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(0.dp,34.dp,0.dp,0.dp)) {

        if(getUserByIdstate?.value?.isLoading == true){
            Text("Loading...")
        }
        else{
            val name = getUserByIdstate?.value?.success?.user?.name?:""

            Text(text="Hi $name Admin has not approved yet. Please wait 24 hours.", modifier = Modifier.fillMaxWidth())



            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {viewModel?.getUserById(userId)}) {
                Text(text="Refresh")
            }
        }

    }






}

@Preview(showBackground = true, showSystemUi=true)
@Composable
fun WaitingScreenPreview(){
    WaitingScreen(
        viewModel = null, navController = null,
        userId = ""
    )
}

