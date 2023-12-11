package main

import core.presentation.navigation.TabItem
import core.presentation.util.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel {
    val tabItems = MutableStateFlow(
        listOf(
            TabItem.HomeTab(),
            TabItem.ExploreTab(),
            TabItem.UpcomingTab(),
            TabItem.MyListTab()
        )
    ).asStateFlow()
}