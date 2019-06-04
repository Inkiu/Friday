package data.api

import domain.model.event.Event
import domain.model.event.Events
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EventApi {
    @GET("v3/events")
    fun getEventsAsync(): Deferred<Events>
}