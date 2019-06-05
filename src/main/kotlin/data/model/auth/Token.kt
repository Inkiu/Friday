package data.model.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Token(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "refresh_token") val refreshToken: String,
    @Json(name = "token_type") val tokenType: String,
    @Json(name = "expires_in") val accessExpireTime: Long,
    @Json(name = "exception") val exception: String = "",
    @Json(name = "error") val error: String = "",
    @Json(name = "error_description") val errorDesc: String = ""
)