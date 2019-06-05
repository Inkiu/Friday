package presentation.bot

import domain.model.ChatContent
import domain.model.PostChat
import javax.inject.Inject

class BotEventDispatcher @Inject constructor(
    private val postChat: PostChat
) {

    private val botMap: MutableMap<RoomKey, Friday> = mutableMapOf()

    fun onChat(chat: ChatContent) {
        val roomKey = RoomKey(chat.teamIndex, chat.roomIndex)
        val friday = botMap[roomKey] ?: Friday(postChat).apply { botMap[roomKey] = this }
        friday.onChatEvent(chat)
    }

    data class RoomKey(
        val teamIndex: Long,
        val roomIndex: Long
    )
}