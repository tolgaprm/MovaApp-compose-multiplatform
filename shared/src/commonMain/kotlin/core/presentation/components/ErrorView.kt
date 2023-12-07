package core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.presentation.theme.dimensions

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    errorMessage: String?,
    onClickRetry: () -> Unit
) {
    Box(
        modifier = modifier.padding(MaterialTheme.dimensions.fourLevel),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
        ) {
            Text(
                text = errorMessage ?: "Something went wrong",
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onClickRetry
            ) {
                Text(text = "Retry")
            }
        }
    }
}