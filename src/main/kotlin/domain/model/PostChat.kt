package domain.model

import domain.repo.ChatRepository

class PostChat(
    private val chatRepository: ChatRepository
) {
    suspend fun get(param: Param): Long {
        return chatRepository.postNormalChat(
            param.teamIndex,
            param.roomIndex,
            param.content
        )
    }

    data class Param(
        val teamIndex: Long,
        val roomIndex: Long,
        val content: String
    )
}