package com.prempal.teachmintassignment.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.prempal.teachmintassignment.ui.viewmodel.GitHubViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController, viewModel: GitHubViewModel = hiltViewModel()) {

    var query by remember { mutableStateOf("") }

    val items by viewModel.repos.observeAsState()

//    // Pagination
//    val pagerState = rememberPagerState()

    Column {
        // Search Bar
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Search Button
        Button(
            onClick = {
                viewModel.searchRepos(query = query, page = 1, perPage = 10)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Search Repositories")
        }

//        // Display Repositories with Pagination
//        HorizontalPager(
//            state = pagerState,
////            count = 2, // Number of pages
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//        ) {
            // Display Repositories
        LazyColumn {

            items?.items?.forEach { item ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onClick = {
//                            navController.navigate("item/${item.id}")
                        }
                    ) {
                        Row(modifier = Modifier.padding(20.dp)) {
                            Column(modifier = Modifier.weight(1f),
                                Arrangement.Center) {
                                Text(
                                    text = item.name ?: "",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Text(
                                    text = item.description ?: "",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                )
                                Text(
                                    text = item.git_url ?: "",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                )
                                Text(
                                    text = "Stars: "+item.stargazers_count.toString() ?: "",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                )
                            }

                        }
                    }
                }
            }
        }
        }
//        PagerTabIndicator(
//            pagerState = pagerState,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        )
//    }
}
