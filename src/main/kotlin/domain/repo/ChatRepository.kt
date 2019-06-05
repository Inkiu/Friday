package domain.repo

import domain.model.ChatContent

interface ChatRepository {

    suspend fun getBotChat(teamIndex: Long, roomIndex: Long, chatIndex: Long, userIndex: Long): ChatContent

    suspend fun postNormalChat(teamIndex: Long, roomIndex: Long, content: String): Long

}