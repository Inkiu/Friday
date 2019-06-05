package data.model.chat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatExtra(
    @Json(name = "input_buttons") val inputButtons: List<TextButton>?,
    @Json(name = "version") val version: Int = 1, // version:1
    @Json(name = "type") val type: String = "answer" // 엑스트라타입 "init":초기화 메시지, "answer":응답 메시지
)

@JsonClass(generateAdapter = true)
data class TextButton(
    @Json(name = "text") val text: String,
    @Json(name = "type") val type: String = "text"
)