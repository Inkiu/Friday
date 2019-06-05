package domain.usecase

import domain.model.chat.Chat
import domain.repo.ChatRepository
import javax.inject.Inject

class GetChat @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend fun get(param: Param): Chat {
        val chat = chatRepository.getChat(param.teamIndex, param.roomIndex, param.chatIndex)
        return if (chat.isSummarized()) {
            val longChatContent = chatRepository.getLongChat(
                param.teamIndex, param.roomIndex, param.chatIndex
            )
            chat.copy(content = longChatContent)
        } else {
            chat
        }
    }

    data class Param(
        val teamIndex: Long,
        val roomIndex: Long,
        val chatIndex: Long
    )
}