package presentation.bot

import app.App
import app.Phase
import domain.usecase.GetEventStream
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class BotActivity(
    app: App
) : Phase, CoroutineScope {
    private val botContext = Dispatchers.Default + Job() + CoroutineExceptionHandler { _, e -> e.printStackTrace()}
    override val coroutineContext: CoroutineContext get() = botContext

    @Inject lateinit var getEventStream: GetEventStream

    init {
        app.appComponent.plus().inject(this)
    }

    @ExperimentalCoroutinesApi
    override fun start() {
        launch {
            val channel = getEventStream.get(CoroutineScope(coroutineContext))
            while(true) println(channel.receive())
        }
    }

    override fun close() {
        coroutineContext.cancelChildren()
    }
}