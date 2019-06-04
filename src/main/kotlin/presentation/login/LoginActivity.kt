package presentation.login

import app.App
import app.Phase
import data.TokenProvider
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginActivity @Inject constructor(
    private val service: LoginService,
    private val tokenProvider: TokenProvider,
    private val app: App
) : Phase, CoroutineScope {
    private val loginContext = Dispatchers.Default + Job() + CoroutineExceptionHandler { _, e -> e.printStackTrace()}
    override val coroutineContext: CoroutineContext get() = loginContext

    override fun start() {
        launch {
            val token = service.botLogin()
            tokenProvider.registerToken(token)
            println(token)
        }
    }

    override fun close() {
        coroutineContext.cancelChildren()
    }
}