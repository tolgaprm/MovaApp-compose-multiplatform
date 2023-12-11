package feature_mylist.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import feature_mylist.presentation.MyListScreen

class MyListScreenRoute : Screen {

    @Composable
    override fun Content() {
        MyListScreen()
    }
}