package data.api

import domain.model.event.Event
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EventApi {
    @GET("v3/events")
    fun getEventsAsync(): Deferred<List<Event>>
}