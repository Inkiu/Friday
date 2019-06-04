package data.repo

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import data.api.AuthApi
import data.api.OAuthApi
import data.model.Auth
import domain.model.Token
import java.io.File
import java.lang.IllegalStateException

class AuthRepository(
    private val oauthApi: OAuthApi,
    private val authApi: AuthApi
) {

    suspend fun botLogin(): Token {
        val auth = getAuth() ?: throw IllegalStateException("can not make auth")
        return oauthApi.login(
            "password",
            auth.clientId,
            auth.clientSecret,
            auth.email,
            auth.password
        ).await()
    }

    private suspend fun getAuth(): Auth? {
        val authJson = File("src/main/resources/auth/teamup_auth.json").readText()
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(Auth::class.java)
            .fromJson(authJson)
    }

}