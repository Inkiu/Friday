package data.repo

import data.api.EventApi
import domain.model.event.Event
import domain.model.event.Events
import domain.repo.EventRepository

class EventRepositoryImpl(
    private val eventApi: EventApi
) : EventRepository {
    override suspend fun getEvents(): Events {
        return eventApi.getEventsAsync().await()
    }
}