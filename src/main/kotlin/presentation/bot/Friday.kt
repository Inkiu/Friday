package presentation.bot

import domain.model.ChatContent
import domain.model.ChatEvent
import domain.model.PostChat
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Friday(
    private val postChat: PostChat
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + Job() + CoroutineExceptionHandler { _, e -> e.printStackTrace() }

    fun onChatEvent(chat: ChatContent) {
        launch {
            postChat.get(PostChat.Param(chat.teamIndex, chat.roomIndex, chat.chatContent))
        }
    }
}