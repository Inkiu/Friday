package domain.model

import domain.repo.ChatRepository
import javax.inject.Inject

class PostChat @Inject constructor(
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