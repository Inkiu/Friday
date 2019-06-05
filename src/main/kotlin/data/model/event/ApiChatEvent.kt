package data.model.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiChatEvent(
    @Json(name = "team") val teamIndex: Long,
    @Json(name = "room") val roomIndex: Long,
    @Json(name = "user") val userIndex: Long,
    @Json(name = "msg") val chatIndex: Long,
    @Json(name = "name") val roomName: String?
)