package feature_splash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.presentation.util.collectAsStateWithLifecycleM
import core.presentation.util.viewModel
import main.navigation.MainScreenRoute
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class SplashScreenRoute : Screen {

    @Composable
    override fun Content() {
        val viewModel = viewModel<SplashViewModel>()
        val uiState = viewModel.state.collectAsStateWithLifecycleM()
        val navigator = LocalNavigator.currentOrThrow
        SplashScreen()

        LaunchedEffect(uiState.consumableEvents) {
            if (uiState.consumableEvents.isNotEmpty()) {
                when (uiState.consumableEvents.first()) {
                    SplashConsumableEvents.NavigateToHome -> {
                        navigator.replace(MainScreenRoute())
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource("mova_logo.xml"),
            contentDescription = "Mova Logo",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}