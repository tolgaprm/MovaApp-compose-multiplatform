package feature_splash.presentation

import core.presentation.util.StateViewModel
import core.presentation.util.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SplashViewModel : StateViewModel<SplashUiData>(SplashUiData()) {

    init {
        viewModelScope.launch {
            delay(2.seconds)
            mutableState.update {
                it.copy(
                    consumableEvents = listOf(SplashConsumableEvents.NavigateToHome)
                )
            }
        }
    }

    fun onConsumableEventConsumed() {
        mutableState.update {
            it.copy(
                consumableEvents = emptyList()
            )
        }
    }
}