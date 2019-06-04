package data.repo

import data.api.EventApi
import domain.model.event.Event
import domain.repo.EventRepository

class EventRepositoryImpl(
    private val eventApi: EventApi
) : EventRepository {
    override suspend fun getEvents(): List<Event> {
        return eventApi.getEventsAsync().await()
    }
}