package domain.model.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Events(
    @Json(name = "events") val events: List<Event>
)

@JsonClass(generateAdapter = true)
data class Event(
    @Json(name = "type") val type: String,
    @Json(name = "chat") val chatEvent: ChatEvent?
) {
    fun isChat() = type == "chat.message"
}

@JsonClass(generateAdapter = true)
data class ChatEvent(
    @Json(name = "team") val teamIndex: Long,
    @Json(name = "room") val roomIndex: Long,
    @Json(name = "user") val userIndex: Long,
    @Json(name = "msg") val chatIndex: Long,
    @Json(name = "name") val roomName: String?
)