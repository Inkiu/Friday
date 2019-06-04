package data.api

import data.model.EventConfig
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EventConfigApi {
    @GET("/")
    fun getConfig() : Deferred<EventConfig>
}