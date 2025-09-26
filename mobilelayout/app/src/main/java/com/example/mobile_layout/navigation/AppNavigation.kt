package com.example.mobile_layout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobile_layout.screens.ConstraintLayoutScreen
import com.example.mobile_layout.screens.LinearLayoutScreen
import com.example.mobile_layout.screens.MainScreen
import com.example.mobile_layout.screens.RelativeLayoutScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.LinearLayoutScreen.route) {
            LinearLayoutScreen(navController = navController)
        }
        composable(Screen.RelativeLayoutScreen.route) {
            RelativeLayoutScreen(navController = navController)
        }
        composable(Screen.ConstraintLayoutScreen.route) {
            ConstraintLayoutScreen(navController = navController)
        }
    }
}
