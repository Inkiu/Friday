package data.repo

import data.api.AuthApi
import data.model.auth.Team
import domain.model.User
import domain.repo.AuthRepository

class AuthRepositoryImpl (
    private val authApi: AuthApi
) : AuthRepository {

    private var authCache: List<Team>? = null

    override suspend fun getMyProfiles(): List<User> {
        return (authCache ?: authApi.getMyTeams().await()).map {
            it.convert()
        }
    }

    private fun Team.convert(): User {
        return User(teamIndex = teamIndex, userIndex = userIndex)
    }

}