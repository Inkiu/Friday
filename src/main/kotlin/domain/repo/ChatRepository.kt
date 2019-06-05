package domain.repo

import domain.model.ChatContent

interface ChatRepository {
    suspend fun getBotChat(teamIndex: Long, roomIndex: Long, chatIndex: Long, userIndex: Long): ChatContent
}