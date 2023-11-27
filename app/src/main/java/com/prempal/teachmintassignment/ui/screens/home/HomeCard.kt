package com.prempal.teachmintassignment.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.prempal.teachmintassignment.data.remote.response.MainResponse
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCard(repo: MainResponse.Item?, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            val name = repo?.githubRepoName ?: ""
            val description = repo?.desc ?: ""
            val contributors = createEncodedURL(repo?.contributorsURL ?: "")
            val gitHubRepoLink = createEncodedURL(repo?.githubRepoURL ?: "")

            navController.navigate("detail/$name/$description/$contributors/$gitHubRepoLink")
        }

    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                Text(
                    text = repo?.githubRepoName ?: "",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = repo?.desc ?: "",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )

            }
        }
    }
}

private fun createEncodedURL(url: String): String? {
    return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
}


