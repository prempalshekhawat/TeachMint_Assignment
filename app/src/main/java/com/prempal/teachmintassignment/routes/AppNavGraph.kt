package com.prempal.teachmintassignment.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prempal.teachmintassignment.ui.screens.home.Home
import com.prempal.teachmintassignment.ui.screens.detail.RepoDetails

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