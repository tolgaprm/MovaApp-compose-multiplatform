package feature_person_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PersonDetailScreen(
    modifier: Modifier = Modifier,
    personDetailUiState: PersonDetailUiState
) {

    Box(
        modifier,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        when (personDetailUiState) {
            is PersonDetailUiState.Loading -> {

            }

            is PersonDetailUiState.Success -> {
                Text(
                    text = personDetailUiState.personDetail.name,
                    color = Color.White
                )
            }

            is PersonDetailUiState.Error -> {
                Text(
                    text = personDetailUiState.message,
                    color = Color.White
                )

            }
        }
    }
}