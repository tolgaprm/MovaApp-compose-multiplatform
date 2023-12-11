package core.presentation.util

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.CoroutineScope
import org.koin.mp.KoinPlatform.getKoin

typealias ViewModel = ScreenModel

typealias StateViewModel<T> = StateScreenModel<T>

@Composable
inline fun <reified T : ViewModel> Screen.viewModel(
): T {
    val koin = getKoin()
    return rememberScreenModel {
        koin.get()
    }
}

val ViewModel.viewModelScope: CoroutineScope get() = screenModelScope