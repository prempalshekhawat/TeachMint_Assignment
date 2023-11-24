package com.prempal.teachmintassignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prempal.teachmintassignment.ui.screens.Home
import com.prempal.teachmintassignment.ui.screens.RepoDetails

@Composable
fun Nav(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){

        composable(route = "repoDetails"){
            RepoDetails(navController)
        }

        composable(route = "home"){
            Home(navController)
        }
    }
}