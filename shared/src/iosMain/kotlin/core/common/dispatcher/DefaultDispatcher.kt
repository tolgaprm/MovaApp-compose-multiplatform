package core.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun createDefaultDispatcher(): DispatcherProvider {
    return DefaultDispatcher()
}

class DefaultDispatcher : DispatcherProvider {
    override val Main: CoroutineDispatcher = Dispatchers.Main
    override val IO: CoroutineDispatcher =
        Dispatchers.Default // In ios does not have Dispatchers.IO
    override val Default: CoroutineDispatcher = Dispatchers.Default
}