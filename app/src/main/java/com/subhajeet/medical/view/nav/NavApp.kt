package com.subhajeet.medical.view.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import androidx.navigation.compose.rememberNavController
import com.subhajeet.medical.R
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
            WaitingScreen(navController)
        }
    }

}