package com.prempal.teachmintassignment.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.prempal.teachmintassignment.ui.viewmodel.HomeViewModel
import com.prempal.teachmintassignment.ui.views.HomeCardView
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.prempal.teachmintassignment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoDetails(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val items by viewModel.items.observeAsState()

//    val movies = remember { items }
//
//    LazyColumn(
//        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
//    ) {
//        items(
//            movies
//        ) {
//            HomeCardView(movie = it)
//        }
//    }

    Column {
        // Display items in a LazyColumn
        LazyColumn {
            items?.forEach { item ->
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
                                    text = item.name,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Text(
                                    text = "category :- "+item.category,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                )
                                Text(
                                    text = "Description :- "+item.desc,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                            Image(painter = rememberAsyncImagePainter(item.imageUrl),
                                contentDescription = "thumbnail",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(110.dp)
                                    .border(
                                        BorderStroke(3.dp, Color.Blue),
                                        CircleShape
                                    )
                                    .padding(3.dp)
                                    .clip(CircleShape)
                            )

                        }
                    }
                }
            }
        }
    }
}