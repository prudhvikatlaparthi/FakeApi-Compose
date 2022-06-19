package com.pru.composeapp.fakeapi.presentation.post_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pru.composeapp.fakeapi.models.Post
import com.pru.composeapp.fakeapi.presentation.posts_master.PostListItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Composable
@Destination
fun PostDetailsScreen(post: Post, resultNavigator: ResultBackNavigator<Boolean>) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        PostListItem(post = post)
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.weight(1f), onClick = {
                resultNavigator.navigateBack(true)
            }) {
                Text(text = "Refresh")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(modifier = Modifier.weight(1f), onClick = {
                resultNavigator.navigateBack()
            }) {
                Text(text = "Back")
            }
        }
    }
}