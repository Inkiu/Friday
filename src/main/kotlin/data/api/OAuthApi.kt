package data.api

import kotlinx.coroutines.Deferred
import data.model.auth.Token
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OAuthApi {

    @FormUrlEncoded
    @POST("oauth2/token")
    fun login(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("username") userName: String,
        @Field("password") password: String
    ): Deferred<Token>

    @FormUrlEncoded
    @POST("oauth2/token")
    fun refreshToken(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ): Deferred<Token>
}