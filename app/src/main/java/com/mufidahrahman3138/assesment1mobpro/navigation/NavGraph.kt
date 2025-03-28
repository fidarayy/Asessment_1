package com.mufidahrahman3138.assesment1mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mufidahrahman3138.assesment1mobpro.ui.MainScreen
import com.mufidahrahman3138.assesment1mobpro.ui.SignUpScreen
import com.mufidahrahman3138.mobpro1.ui.screen.AboutScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignUp.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                onSignUpSuccess = { navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
