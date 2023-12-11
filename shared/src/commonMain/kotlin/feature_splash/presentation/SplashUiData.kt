package feature_splash.presentation

data class SplashUiData(
    val consumableEvents: List<SplashConsumableEvents> = emptyList(),
)

sealed class SplashConsumableEvents {
    data object NavigateToHome : SplashConsumableEvents()
}