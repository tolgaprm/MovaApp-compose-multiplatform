package core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import core.presentation.theme.dimensions

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Icon(
        modifier = modifier.clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(MaterialTheme.dimensions.twoLevel),
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = null,
        tint = contentColor
    )
}