package data

import data.api.OAuthApi
import kotlinx.coroutines.runBlocking
import okhttp3.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val oauthApi: OAuthApi
) : Authenticator, Interceptor {

    val AUTHORIZATION = "Authorization"
    val REFRESH_PARAM = "PARAM_NAME_REFRESH_TOKEN"

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(makeTokenAddedRequest(chain.request()))
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            // Auth 없이 사용된 것이라면
            if (response.request().header(AUTHORIZATION) == null) return null
            val usedToken = response.request().header(AUTHORIZATION)

            // 변경이 있었다면
            val mightNewToken = tokenProvider.getAuthorization()
            if (usedToken != mightNewToken) return makeTokenAddedRequest(response.request())

            // 새로운 토큰을 가져옴
            val token = runBlocking {
                try {
                    oauthApi.refreshToken(REFRESH_PARAM, tokenProvider.getRefreshToken()).await()
                } catch (e: Exception) {
                    null
                }
            }

            return if (token != null) {
                tokenProvider.registerToken(token)
                makeTokenAddedRequest(response.request())
            } else {
                tokenProvider.expireToken()
                null
            }
        }
    }

    private fun makeTokenAddedRequest(origin: Request): Request {
        return origin.newBuilder()
            .header(AUTHORIZATION, tokenProvider.getAuthorization())
            .method(origin.method(), origin.body())
            .build()
    }


}