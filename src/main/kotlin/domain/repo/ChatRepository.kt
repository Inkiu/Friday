package domain.repo

import domain.model.chat.Chat

interface ChatRepository {
    suspend fun getChat(teamIndex: Long, roomIndex: Long, chatIndex: Long): Chat
    suspend fun getLongChat(teamIndex: Long, roomIndex: Long, chatIndex: Long): String
}