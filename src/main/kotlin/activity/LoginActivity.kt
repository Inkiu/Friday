package activity

import app.App
import app.Phase
import kotlinx.coroutines.*
import presentation.LoginService
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginActivity @Inject constructor(
    private val service: LoginService,
    private val app: App
) : Phase, CoroutineScope {
    private val loginContext = Dispatchers.Default + Job() + CoroutineExceptionHandler { _, e -> e.printStackTrace()}
    override val coroutineContext: CoroutineContext get() = loginContext

    override fun start() {
        launch {
            println(service.botLogin())
        }
    }

    override fun close() {
        coroutineContext.cancelChildren()
    }
}