package core.presentation.util

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow

/**
Android side called `collectAsStateWithLifecycle`,
The StateFlow will be collected when the Composable function is in the
Lifecycle.State.STARTED state and will stop collecting when the Composable
function is in the Lifecycle.State.STOP state.

iOS side called `collectAsState`,
 */
@Composable
expect fun <T> StateFlow<T>.collectAsStateWithLifecycleM(): T