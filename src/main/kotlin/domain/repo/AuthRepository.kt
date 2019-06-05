package domain.repo

import domain.model.User

interface AuthRepository {
    suspend fun getMyProfiles(): List<User>
}