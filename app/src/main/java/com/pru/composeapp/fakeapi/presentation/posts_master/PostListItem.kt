package com.pru.composeapp.fakeapi.presentation.posts_master

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pru.composeapp.fakeapi.models.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListItem(post: Post, lastItem: Boolean = false, onTap: ((post: Post) -> Unit)? = null) {
    Card(
        modifier = Modifier.padding(top = 10.dp, bottom = if (lastItem) 10.dp else 0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (onTap == null) 0.dp else 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), onClick = {
            onTap?.invoke(post)
        }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Id : ${post.id}",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Title : ${post.title}",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Body : ${post.body}",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}