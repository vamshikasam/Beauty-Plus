package com.beautyplus.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("signup_screen")
    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen("detail_screen")
    object BookingScreen: Screen("booking_screen")

}