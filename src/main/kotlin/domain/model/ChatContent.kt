package domain.model

import data.model.chat.ChatFile

data class ChatContent(
    val teamIndex: Long,
    val roomIndex: Long,
    val chatIndex: Long,
    val userIndex: Long,
    val chatType: Int,
    val chatContent: String,
    val chatInit: Boolean,
    val chatFile: ChatFile? = null
)