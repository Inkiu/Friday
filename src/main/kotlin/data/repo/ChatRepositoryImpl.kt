package data.repo

import data.api.EdgeApi
import domain.model.ChatContent
import data.model.chat.Chat
import domain.repo.ChatRepository

class ChatRepositoryImpl (
    private val edgeApi: EdgeApi
) : ChatRepository {

    override suspend fun getBotChat(teamIndex: Long, roomIndex: Long, chatIndex: Long, userIndex: Long): ChatContent {
        val chatSummary = getChat(roomIndex, chatIndex)
        val chat = if (chatSummary.contentLength != chatSummary.content.length) {
            chatSummary.copy(content = getLongChat(roomIndex, chatIndex))
        } else {
            chatSummary
        }
        return chat.convert(roomIndex, chatIndex, userIndex)
    }

    private suspend fun getChat(roomIndex: Long, chatIndex: Long): Chat {
        return edgeApi.getMessageSummary(roomIndex, chatIndex).await()
    }

    private suspend fun getLongChat(roomIndex: Long, chatIndex: Long): String {
        return edgeApi.getLongMessage(roomIndex, chatIndex).await()
    }

    private fun Chat.convert(
        roomIndex: Long,
        chatIndex: Long,
        userIndex: Long
    ): ChatContent {
        return ChatContent(
            teamIndex,
            roomIndex,
            chatIndex,
            userIndex,
            type,
            content,
            isInit(),
            file
        )
    }
}