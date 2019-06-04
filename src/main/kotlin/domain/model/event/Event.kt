package domain.model.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event (
    @Json(name = "type") val type: String,
    @Json(name = "chat") val chatEvent: ChatEvent?
) {
    fun isChat() = type == "chat_message"
}