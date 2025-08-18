package com.subhajeet.medical.view.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.subhajeet.medical.R
import com.subhajeet.medical.view.HomeScreen
import com.subhajeet.medical.view.LoginScreen
import com.subhajeet.medical.view.SignUpScreen
import com.subhajeet.medical.view.WaitingScreen

@Composable
fun NavApp() {

    val navController = rememberNavController()

    NavHost(navController , startDestination = Routes.LoginRoutes ){

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
    }

}