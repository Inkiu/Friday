package presentation.bot

import app.App
import app.Phase
import domain.usecase.GetChat
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
    @Inject lateinit var getChat: GetChat

    init {
        app.appComponent.plus().inject(this)
    }

    @ExperimentalCoroutinesApi
    override fun start() {
        launch {
            val channel = getEventStream.get(CoroutineScope(coroutineContext))
            while(true) {
                val chatEvent = channel.receive()
                println(getChat.get(GetChat.Param(chatEvent.teamIndex, chatEvent.roomIndex, chatEvent.chatIndex, chatEvent.userIndex)))
            }
        }
    }

    override fun close() {
        coroutineContext.cancelChildren()
    }
}