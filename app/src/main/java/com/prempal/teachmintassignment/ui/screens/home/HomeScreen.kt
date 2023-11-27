package com.prempal.teachmintassignment.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.prempal.teachmintassignment.ui.screens.MainViewModel
import com.prempal.teachmintassignment.utils.NetworkUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    var query by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    val lazyColumnListState = rememberLazyListState()

    if (query == ""){
        searchQuery = query
    }

    val dataset = viewModel.items(searchQuery).collectAsLazyPagingItems()

    Column {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = {
                searchQuery = query
//                viewModel.updateQuery(query = query)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Search Repositories")
        }

    LazyColumn(state = lazyColumnListState) {
        items(
        count = dataset.itemCount,
        key = dataset.itemKey(),
        contentType = dataset.itemContentType(
            )
    ) { index ->
        val item = dataset[index]
        HomeCard(item, navHostController)
        }

        when (dataset.loadState.refresh) {
            is LoadState.Error -> {
                //ToDo: to be handled
            }

            is LoadState.Loading -> { // Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Loading"
                        )

                        CircularProgressIndicator(color = Color.Blue)
                    }
                }
            }

            else -> {}
        }

    }
    }
}
