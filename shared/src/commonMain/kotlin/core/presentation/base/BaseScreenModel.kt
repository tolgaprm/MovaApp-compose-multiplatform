package core.presentation.base

import cafe.adriel.voyager.core.model.StateScreenModel

abstract class BaseScreenModel<State : Any, Event : Any>(
    initialState: State
) : StateScreenModel<State>(initialState) {

    abstract fun onEvent(event: Event)
}