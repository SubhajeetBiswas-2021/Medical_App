package com.subhajeet.medical.view

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medical.prf.UserPreferences
import com.subhajeet.medical.viewModel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController,viewModel: MyViewModel?= hiltViewModel()) {

    val context: Context = LocalContext.current
    val userPreferences = UserPreferences(context)

    val userId = userPreferences.getUserId.collectAsState(initial = null).value

    val getUserByIdstate = viewModel?.getUserByIdstate?.collectAsState()

    var name = getUserByIdstate?.value?.success?.user?.name
    var phoneNumber = getUserByIdstate?.value?.success?.user?.phone_number
    //val userId = getUserByIdstate?.value?.success?.user?.user_id
    var isApproved = (getUserByIdstate?.value?.success?.user?.isApproved ?: 0).toInt()

    LaunchedEffect(userId) {
        if (viewModel != null) {
            if (userId != null) {
                viewModel.getUserById(userId)
            }
        }
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,   // Background color
                    titleContentColor = Color.White,       //  Title color
                    navigationIconContentColor = Color.White, // ≡ Menu icon color
                    actionIconContentColor = Color.White
                )
            )
        }


    ) {innerPadding->

    Column(modifier = Modifier.padding(innerPadding)) {

        OutlinedTextField(
            value = "Name: $name",
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 5.dp, 5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "Phone Number:${phoneNumber}",
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 5.dp, 5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = if (isApproved == 1) "Status: Approved" else "Status:Blocked",
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 5.dp, 5.dp)
        )


    }
}


}