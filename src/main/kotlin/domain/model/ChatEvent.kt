package domain.model

data class ChatEvent(
    val teamIndex: Long,
    val roomIndex: Long,
    val userIndex: Long,
    val chatIndex: Long,
    val roomName: String?
)