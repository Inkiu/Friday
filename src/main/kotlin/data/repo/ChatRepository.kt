package data.repo

import data.api.EdgeApi
import domain.model.chat.Chat
import domain.repo.ChatRepository

class ChatRepositoryImpl (
    private val edgeApi: EdgeApi
) : ChatRepository {
    override suspend fun getChat(teamIndex: Long, roomIndex: Long, chatIndex: Long): Chat {
        return edgeApi.getMessageSummary(roomIndex, chatIndex).await()
    }

    override suspend fun getLongChat(teamIndex: Long, roomIndex: Long, chatIndex: Long): String {
        return edgeApi.getLongMessage(roomIndex, chatIndex).await()
    }
}