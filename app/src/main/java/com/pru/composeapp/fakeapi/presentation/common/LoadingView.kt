package com.pru.composeapp.fakeapi.presentation.posts_master

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pru.composeapp.fakeapi.R

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(modifier = Modifier) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = stringResource(R.string.loading))
        }
    }
}