package feature_person_detail.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.components.MovaImage
import core.presentation.components.verticalGradientRect
import core.presentation.theme.dimensions
import feature_person_detail.domain.model.combinedCredits.PersonCast
import feature_person_detail.domain.model.combinedCredits.PersonCrew
import feature_person_detail.domain.model.combinedCredits.PersonWorks
import feature_person_detail.domain.model.combinedCredits.PersonWorksType
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

        AsWorksSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimensions.fourLevel),
            sectionTitle = "As Actor's Works",
            personWorks = uiState.personDetail.combinedCredit?.cast ?: emptyList()
        )

        AsWorksSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimensions.fourLevel),
            sectionTitle = "As Director's Works",
            personWorks = uiState.personDetail.combinedCredit?.crew ?: emptyList()
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
    var isExpandedBio by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)

    ) {
        Column {
            InfoSectionRow(
                modifier = Modifier.fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = 0.8f,
                            stiffness = 100f
                        )
                    ),
                title = "Biography",
                infoMessage = biography,
                maxLines = if (isExpandedBio) Int.MAX_VALUE else 5
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.dimensions.twoLevel)
                    .clickable { isExpandedBio = !isExpandedBio },
                text = if (isExpandedBio) "Show Less" else "Show More",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                textAlign = TextAlign.End,
            )
        }

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
    infoMessage: String,
    maxLines: Int = Int.MAX_VALUE
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
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            maxLines = maxLines
        )
    }
}


@Composable
private fun <T : PersonWorks> AsWorksSection(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    personWorks: List<T>
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.padding(top = MaterialTheme.dimensions.twoLevel))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.fourLevel)
        ) {
            items(
                items = personWorks,
                key = { it.id }
            ) { item ->
                when (item.personWorksType) {
                    PersonWorksType.CAST -> {
                        val personCast = item as? PersonCast ?: return@items
                        PersonWorkItem(
                            posterImageUrl = item.posterPath,
                            mediaType = item.mediaType,
                            title = "Character",
                            subtitle = personCast.character
                        )
                    }

                    PersonWorksType.CREW -> {
                        val personCrew = item as? PersonCrew ?: return@items
                        PersonWorkItem(
                            posterImageUrl = item.posterPath,
                            mediaType = item.mediaType,
                            title = "Department",
                            subtitle = personCrew.department
                        )
                    }
                }
            }
        }
    }
}