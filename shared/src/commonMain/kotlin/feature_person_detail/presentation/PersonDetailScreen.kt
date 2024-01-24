package feature_person_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.presentation.components.BackButton
import core.presentation.components.ErrorView
import core.presentation.components.MCircularProgressIndicator
import core.presentation.theme.dimensions
import feature_person_detail.presentation.components.PersonDetailSuccess

@Composable
fun PersonDetailScreen(
    modifier: Modifier = Modifier,
    personDetailUiState: PersonDetailUiState,
    onPopBackStack: () -> Unit,
    onEvent: (PersonDetailEvent) -> Unit
) {
    Box(
        modifier
    ) {
        when (personDetailUiState) {
            is PersonDetailUiState.Loading -> {
                MCircularProgressIndicator()
            }

            is PersonDetailUiState.Success -> {
                PersonDetailSuccess(
                    modifier = Modifier.fillMaxSize(),
                    uiState = personDetailUiState
                )

                BackButton(
                    modifier = Modifier
                        .padding(MaterialTheme.dimensions.fourLevel)
                        .align(Alignment.TopStart),
                    onClick = onPopBackStack
                )
            }

            is PersonDetailUiState.Error -> {
                ErrorView(
                    modifier = Modifier.fillMaxSize()
                        .align(Alignment.Center),
                    errorMessage = personDetailUiState.message,
                    onClickRetry = {
                        onEvent(PersonDetailEvent.OnTryAgainClicked)
                    }
                )
            }
        }
    }
}