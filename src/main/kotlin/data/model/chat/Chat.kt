package data.model.chat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Chat(
    @Json(name = "team") val teamIndex: Long,
    @Json(name = "user") val userIndex: Long,
    @Json(name = "len") val contentLength: Int,
    @Json(name = "type") val type: Int,     // 메시지타입 1:일반, 2:파일, 3:초대, 4:퇴장
    @Json(name = "content") val content: String,
    @Json(name = "file") val file: ChatFile? = null,
    @Json(name = "extras") val extras: List<ChatExtra>? = null
) {
    fun isInit(version: Int = 1) = extras?.any { it.version == version && it.type == "init" } ?: false
    fun isTypeNormal() = type == 1
    fun isTypeFile() = type == 2
    fun isSummarized() = contentLength != content.length
}
