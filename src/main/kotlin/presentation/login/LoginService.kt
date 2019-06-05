package presentation.login

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import data.api.EventConfigApi
import data.api.OAuthApi
import data.model.auth.Auth
import data.model.EventConfig
import data.model.auth.Team
import data.model.auth.Token
import kotlinx.coroutines.Deferred
import java.io.File
import java.lang.IllegalStateException

class LoginService(
    private val oauthApi: OAuthApi,
    private val eventApi: EventConfigApi,
    private val authFilePath: String
) {
    fun botLogin(): Deferred<Token> {
        val auth = getAuth() ?: throw IllegalStateException("can not make auth")
        return oauthApi.login(
            "password",
            auth.clientId,
            auth.clientSecret,
            auth.email,
            auth.password
        )
    }

    fun getConfig(): Deferred<EventConfig> {
        return eventApi.getConfig()
    }

    private fun getAuth(): Auth? {
        val authJson = File(authFilePath).readText()
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            .adapter(Auth::class.java)
            .fromJson(authJson)
    }
}