package domain.usecase

import domain.model.ChatContent
import domain.repo.ChatRepository
import javax.inject.Inject

class GetChat @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend fun get(param: Param): ChatContent {
        return chatRepository.getBotChat(param.teamIndex, param.roomIndex, param.chatIndex, param.userIndex)
    }

    data class Param(
        val teamIndex: Long,
        val roomIndex: Long,
        val chatIndex: Long,
        val userIndex: Long
    )
}