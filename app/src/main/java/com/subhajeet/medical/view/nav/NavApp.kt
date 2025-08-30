package com.subhajeet.medical.view.nav


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
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

    val bottomNavItems= listOf(
        BottomNavItem("Home",icon = Icons.Default.Home),
        BottomNavItem("Add",icon = Icons.Default.Add),
        BottomNavItem("Profile",icon = Icons.Default.Person),
        BottomNavItem("Settings",icon = Icons.Default.Settings)
    )
    var selected by remember { mutableIntStateOf(0) } // managing state for for which bottomNavItems is selected

    Scaffold(
        bottomBar = {
            NavigationBar {

                bottomNavItems.forEachIndexed{index, item ->
                    NavigationBarItem(
                        alwaysShowLabel = true,
                        selected= selected == index,
                        onClick = {

                            selected = index  //updating the state
                            when(index) {
                                0-> navController.navigate(Routes.HomeRoutes)
                                1-> navController.navigate(Routes.ProfileRoutes)

                            }

                        },
                        icon = {
                            Icon(
                                imageVector = item.icon, contentDescription = item.name
                                )
                        }
                    )
                }

            }
        }
    ) { innerPadding ->


        NavHost(navController, startDestination = startScreen) {

            composable<Routes.LoginRoutes> {
                LoginScreen(navController)
            }

            composable<Routes.SignUpRoutes> {
                SignUpScreen(navController)
            }

            composable<Routes.WaitingRoutes> {
                val data = it.toRoute<Routes.WaitingRoutes>()
                WaitingScreen(navController, userId = data.userId)
            }

            composable<Routes.HomeRoutes> {
                HomeScreen(navController)
            }

            composable<Routes.OrderRoutes> {
                val data = it.toRoute<Routes.OrderRoutes>()
                OrderScreen(navController, productId = data.productId)
            }

            composable<Routes.ProfileRoutes> {
                LoginScreen((navController))
            }
        }


    }

}

data class BottomNavItem(
    val name: String,
    val icon:ImageVector
)