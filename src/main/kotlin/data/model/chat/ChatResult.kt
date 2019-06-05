package data.model.chat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatResult(
    @Json(name = "msg") val msgIndex: Long
)