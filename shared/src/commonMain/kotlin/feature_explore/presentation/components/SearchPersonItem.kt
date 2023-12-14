package feature_explore.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import core.presentation.components.MovaImage
import core.presentation.theme.dimensions
import feature_explore.domain.model.PersonSearch
import feature_explore.presentation.model.SearchItemType

@Composable
fun SearchPersonItem(
    modifier: Modifier = Modifier,
    personSearch: PersonSearch
) {
    Card(
        modifier = modifier
            .width(165.dp)
            .height(350.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                MovaImage(
                    imageUrl = personSearch.personImageUrl,
                    isShowLoading = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(MaterialTheme.dimensions.twoLevel),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.twoLevel)
                ) {
                    Text(
                        text = personSearch.name,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography
                            .labelLarge.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1
                    )
                    Text(
                        text = personSearch.knownForDepartment,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }

            SearchItemTypeText(
                modifier = Modifier.align(Alignment.TopEnd),
                searchItemType = SearchItemType.PERSON
            )
        }
    }
}