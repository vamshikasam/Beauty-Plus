package com.beautyplus.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beautyplus.ui.booking.BookingScreen
import com.beautyplus.ui.booking_history.BookingHistoryScreen
import com.beautyplus.ui.detail.DetailScreen
import com.beautyplus.ui.login.LoginScreen
import com.beautyplus.ui.main.MainScreen
import com.beautyplus.ui.sign_up.SignUpScreen
import com.beautyplus.ui.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUpScreen.route) { navBackStack ->
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) { navBackStack ->
            MainScreen(navController = navController)
        }
        composable(route = Screen.DetailScreen.route) { navBackStack ->
            DetailScreen(navController = navController)
        }
        composable(route = Screen.BookingScreen.route) { navBackStack ->
            BookingScreen(navController = navController)
        }
        composable(route = Screen.BookingHistory.route) { navBackStack ->
            BookingHistoryScreen(navController = navController)
        }
    }

}