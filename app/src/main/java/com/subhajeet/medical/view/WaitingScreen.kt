package com.subhajeet.medical.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medical.viewModel.MyViewModel

@Composable
fun WaitingScreen(navController: NavController, userId: String,viewModel: MyViewModel?= hiltViewModel()) {


    val getUserByIdstate = viewModel?.getUserByIdstate?.collectAsState()

    LaunchedEffect(userId) {
        if (viewModel != null) {
            viewModel.getUserById(userId)
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text="Waiting Screen", modifier = Modifier.fillMaxSize())
    }

}