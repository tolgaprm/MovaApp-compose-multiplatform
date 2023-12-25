package feature_person_detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.presentation.components.MovaImage
import core.presentation.components.verticalGradientRect
import core.presentation.theme.dimensions
import feature_person_detail.presentation.PersonDetailUiState

@Composable
fun PersonDetailSuccess(
    modifier: Modifier = Modifier,
    uiState: PersonDetailUiState.Success
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        TopSectionWithPoster(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            profilePath = uiState.personDetail.profilePath,
            personName = uiState.personDetail.name
        )

        InfoSection(
            modifier = Modifier.fillMaxWidth()
                .padding(MaterialTheme.dimensions.fourLevel),
            biography = uiState.personDetail.biography,
            dateOfBirth = uiState.personDetail.birthday,
            dateOfDeath = uiState.personDetail.deathDay,
            placeOfBirth = uiState.personDetail.placeOfBirth
        )
    }
}

@Composable
private fun TopSectionWithPoster(
    modifier: Modifier = Modifier,
    profilePath: String?,
    personName: String
) {
    Box(
        modifier = modifier
    ) {
        MovaImage(
            modifier = Modifier.fillMaxSize(),
            imageUrl = profilePath
        )

        Text(
            modifier = Modifier
                .verticalGradientRect()
                .padding(MaterialTheme.dimensions.fourLevel)
                .fillMaxWidth()
                .align(alignment = Alignment.BottomStart),
            text = personName,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
private fun InfoSection(
    modifier: Modifier = Modifier,
    biography: String,
    dateOfBirth: String?,
    dateOfDeath: String?,
    placeOfBirth: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)

    ) {
        InfoSectionRow(
            modifier = Modifier.fillMaxWidth(),
            title = "Biography",
            infoMessage = biography
        )

        dateOfBirth?.let {
            InfoSectionRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Date of Birth",
                infoMessage = dateOfBirth
            )
        }

        dateOfDeath?.let {
            InfoSectionRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Date of Death",
                infoMessage = dateOfDeath
            )
        }

        InfoSectionRow(
            modifier = Modifier.fillMaxWidth(),
            title = "Place of Birth",
            infoMessage = placeOfBirth
        )
    }
}

@Composable
private fun InfoSectionRow(
    modifier: Modifier = Modifier,
    title: String,
    infoMessage: String
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "$title:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.padding(end = MaterialTheme.dimensions.twoLevel))
        Text(
            text = infoMessage,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}