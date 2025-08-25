package com.subhajeet.medical.view.nav


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.subhajeet.medical.view.HomeScreen
import com.subhajeet.medical.view.LoginScreen
import com.subhajeet.medical.view.OrderScreen
import com.subhajeet.medical.view.SignUpScreen
import com.subhajeet.medical.view.WaitingScreen
import com.subhajeet.medical.viewModel.MyViewModel

@Composable
fun NavApp(viewModel: MyViewModel= hiltViewModel()) {

    val navController = rememberNavController()

    var userId by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.userPreferences.getUserId.collect{
            userId = it ?:""
        }
        isLoading=false

    }

    val startScreen = remember ( userId ){
        if(userId.isNotEmpty()){
            Routes.WaitingRoutes(userId)
        }else{
            Routes.LoginRoutes
        }

    }
    NavHost(navController , startDestination = startScreen ){

        composable<Routes.LoginRoutes>{
            LoginScreen(navController)
        }

        composable<Routes.SignUpRoutes>{
            SignUpScreen(navController)
        }

        composable<Routes.WaitingRoutes>{
            val data = it.toRoute<Routes.WaitingRoutes>()
            WaitingScreen(navController , userId = data.userId)
        }

        composable<Routes.HomeRoutes>{
            HomeScreen(navController)
        }

        composable<Routes.OrderRoutes> {
            val data = it.toRoute<Routes.OrderRoutes>()
            OrderScreen(navController, productId = data.productId)
        }
    }

}