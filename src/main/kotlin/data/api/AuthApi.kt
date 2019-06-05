package data.api

import data.model.auth.Team
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AuthApi {
    @GET("v3/licenses")
    fun getMyTeams(
    ) : Deferred<List<Team>>
}