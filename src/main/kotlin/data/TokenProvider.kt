package data

import data.model.Token

class TokenProvider {

    private var token: Token? = null

    fun registerToken(token: Token) {
        synchronized(this) {
            this.token = token.copy()
        }
    }

    fun expireToken() {
        synchronized(this) {
            this.token = null
        }
    }

    fun getAuthorization(): String {
        val localToken = token
        return if (localToken != null) {
            "${localToken.tokenType} ${localToken.accessToken}"
        } else {
            ""
        }
    }

    fun getRefreshToken(): String {
        return token?.refreshToken ?: ""
    }
}