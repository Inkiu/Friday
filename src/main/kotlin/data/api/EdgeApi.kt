package data.api

import data.model.ChatRequest
import domain.model.chat.Chat
import domain.model.chat.ChatLong
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface EdgeApi {
    @GET("v3/message/summary/{room}/{msg}/{confirm}")
    fun getMessageSummary(
        @Path("room") roomIndex: Long,
        @Path("msg") chatIndex: Long,
        @Path("confirm") confirm: Int = 1
    ): Deferred<Chat>

    @GET("v3/message/{roomIndex}/{messageIndex}")
    fun getLongMessage(
        @Path("roomIndex") roomIndex: Long,
        @Path("messageIndex") chatIndex: Long
    ): Deferred<String>

    @POST("v3/message/{room}/{type}")
    fun postMessage(
        @Header("Content-Type") contentType: String = "application/json; charset=utf-8",
        @Path("room") roomIndex: Long,
        @Path("type") type: Int = 1, // 1 : 일반, 2 : 파일
        @Body requestBody: ChatRequest
    ): Deferred<Chat>
}

