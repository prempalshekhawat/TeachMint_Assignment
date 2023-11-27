package com.prempal.teachmintassignment.routes

import android.util.JsonReader
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prempal.teachmintassignment.data.remote.response.MainResponse
import com.prempal.teachmintassignment.routes.Destinations.DETAIL_ROUTE
import com.prempal.teachmintassignment.routes.Destinations.HOME_ROUTE
import com.prempal.teachmintassignment.ui.screens.detail.RepoDetails
import com.prempal.teachmintassignment.ui.screens.home.Home

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_ROUTE) {

        composable(route = "$DETAIL_ROUTE/{name}/{description}/{contributors}/{gitHubRepoLink}") { navBackStack ->
            val name = navBackStack.arguments?.getString("name")
            val description = navBackStack.arguments?.getString("description")
            val contributors = navBackStack.arguments?.getString("contributors")
            val gitHubRepoLink = navBackStack.arguments?.getString("gitHubRepoLink")

                RepoDetails(name = name,
                    description = description,
                    contributors = contributors,
                    gitHubRepoLink = gitHubRepoLink,
                    navController
                )

        }

        composable(route = HOME_ROUTE) {
            Home(navController)
        }
    }
}

object Destinations {
    const val DETAIL_ROUTE = "detail"
    const val HOME_ROUTE = "home"
}