package presentation.bot

import di.ImageFolder
import domain.model.ChatContent
import domain.model.PostChat
import domain.usecase.RegisterFile
import javax.inject.Inject

class BotEventDispatcher @Inject constructor(
    private val postChat: PostChat,
    private val registerFile: RegisterFile
) {

    private val botMap: MutableMap<RoomKey, Friday> = mutableMapOf()

    fun onChat(chat: ChatContent) {
        val roomKey = RoomKey(chat.teamIndex, chat.roomIndex)
        val friday = botMap[roomKey] ?: createFriday(chat).apply { botMap[roomKey] = this }
        friday.onChatEvent(chat)
    }

    private fun createFriday(chat: ChatContent): Friday {
        return Friday(
            postChat,
            registerFile,
            chat.teamIndex,
            chat.roomIndex
        )
    }

    data class RoomKey(
        val teamIndex: Long,
        val roomIndex: Long
    )
}