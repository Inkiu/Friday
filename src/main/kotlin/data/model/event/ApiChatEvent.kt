package data.model.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiChatEvent(
    @Json(name = "team") val teamIndex: Long = -1,
    @Json(name = "room") val roomIndex: Long = -1,
    @Json(name = "user") val userIndex: Long = -1,
    @Json(name = "msg") val chatIndex: Long = -1,
    @Json(name = "name") val roomName: String?
)