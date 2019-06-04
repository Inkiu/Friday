package data.model

data class Auth (
    val email: String,
    val password: String,
    val clientId: String,
    val clientSecret: String
)