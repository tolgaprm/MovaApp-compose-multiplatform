package feature_person_detail.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import feature_person_detail.presentation.PersonDetailEvent
import feature_person_detail.presentation.PersonDetailScreen
import feature_person_detail.presentation.PersonDetailViewModel

data class PersonDetailRoute(
    val personId: Int
) : Screen {
    @Composable
    override fun Content() {
        val personDetailViewModel = viewModel<PersonDetailViewModel>()
        val personDetailUiState = personDetailViewModel.state.collectAsStateWithLifecycleM()

        PersonDetailScreen(
            modifier = Modifier.fillMaxSize(),
            personDetailUiState = personDetailUiState
        )

        LaunchedEffect(Unit) {
            personDetailViewModel.onEvent(PersonDetailEvent.GetPersonDetail(personId))
        }
    }
}