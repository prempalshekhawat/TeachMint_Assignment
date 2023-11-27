package com.prempal.teachmintassignment.ui.screens.detail

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.prempal.teachmintassignment.data.remote.response.MainResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoDetails(name: String?,
                description: String?,
                contributors: String?,
                gitHubRepoLink: String?,
                navHostController:NavHostController) {

    var showWebView by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                Text(
                    text = name.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = description.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )

                Text(
                    text = contributors.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )

                ClickableText(text = buildAnnotatedString {
                    append("Github Repo link: ")
                    withStyle(style = SpanStyle(color = Color.Blue), ) {
                        append(gitHubRepoLink.toString())
                    }
                }, onClick = {
                    showWebView = true
                })

                Spacer(modifier = Modifier.height(20.dp))

                if (showWebView) {
                            Webview(url = gitHubRepoLink.toString())
                }

            }
        }
    }
}