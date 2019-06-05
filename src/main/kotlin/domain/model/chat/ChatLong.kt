package domain.model.chat

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatLong(
    val content: String
)