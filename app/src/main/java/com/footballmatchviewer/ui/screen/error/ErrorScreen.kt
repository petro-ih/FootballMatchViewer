package com.footballmatchviewer.ui.screen.error

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.footballmatchviewer.R

@Composable
fun ErrorScreen(
    @StringRes
    title: Int,
    @StringRes
    description: Int,
    @StringRes
    ctaText: Int,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.CloudOff,
                contentDescription = stringResource(id = R.string.error_no_internet_title),
                tint = Color.Gray,
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 24.dp)
            )

            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onRetry) {
                Text(text = stringResource(id = ctaText))
            }
        }
    }
}

@Composable
fun NoInternetConnectionScreen(onRetry: () -> Unit) {
    ErrorScreen(
        title = R.string.error_no_internet_title,
        description = R.string.error_no_internet_description,
        ctaText = R.string.error_no_internet_cta,
        onRetry = onRetry
    )
}

@Composable
fun GenericErrorScreen(onRetry: () -> Unit) {
    ErrorScreen(
        title = R.string.error_generic_title,
        description = R.string.error_generic_description,
        ctaText = R.string.error_generic_cta,
        onRetry = onRetry
    )
}