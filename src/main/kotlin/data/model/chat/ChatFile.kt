package data.model.chat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatFile(
    @Json(name = "name") val name: String,
    @Json(name = "size") val size: Long,
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: String
) {
    fun isTypeNormal(): Boolean = type == "normal"
    fun isTypeImage(): Boolean = type == "image"
    fun isTypeVideo(): Boolean = type == "video"
}