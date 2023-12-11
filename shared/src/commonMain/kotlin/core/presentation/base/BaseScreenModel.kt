package core.presentation.base

import cafe.adriel.voyager.core.model.StateScreenModel
import core.common.Resource

abstract class BaseScreenModel<State : Any, Event : Any>(
    initialState: State
) : StateScreenModel<State>(initialState) {

    abstract fun onEvent(event: Event)

    /**
     * Handles a resource operation with optional success and error callbacks.
     *
     * This function is designed to simplify resource handling by providing callbacks for both
     * success and error cases. It executes the `resourceSupplier` lambda to obtain the resource,
     * and then it invokes the appropriate callback based on the result.
     *
     * @param resourceSupplier A lambda that supplies the resource to be processed.
     * @param onErrorCallback A callback to be executed in case of an error, with a `UiText` parameter
     *                        representing the error message or text to be displayed.
     * @param onSuccessCallback A callback to be executed when the resource operation succeeds.
     *
     * Usage:
     * ```kotlin
     * handleResourceWithCallbacks(
     *     resourceSupplier = { fetchDataFromServer() },
     *     onErrorCallback = {message-> _uiState.update{it.copy(error= message)} },
     *     onSuccessCallback = { data ->
     *         _uiState.update{}
     *     }
     * )
     * ```
     *
     * @param T The type of the resource being handled.
     */
    protected inline fun <T> handleResourceWithCallbacks(
        resourceSupplier: () -> Resource<T>,
        crossinline onSuccessCallback: (T) -> Unit,
        crossinline onErrorCallback: (String) -> Unit
    ) {
        when (val response = resourceSupplier()) {
            is Resource.Success -> {
                onSuccessCallback(response.data!!)
            }

            is Resource.Error -> {
                onErrorCallback(response.error?.message ?: "Something went wrong")
            }
        }
    }
}