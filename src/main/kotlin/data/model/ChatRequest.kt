package data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import data.model.chat.ChatExtra

@JsonClass(generateAdapter = true)
data class ChatRequest(
    @Json(name = "content") val content: String,
    @Json(name = "extras") val extras: List<ChatExtra>? = null
)