package com.mufidahrahman3138.assesment1mobpro.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("mainScreen")
    data object About : Screen("aboutScreen")
    data object SignUp : Screen("signUpScreen")
}
