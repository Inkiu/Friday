package di.event

import dagger.Binds
import dagger.Module
import dagger.Provides
import data.api.EventApi
import data.repo.EventRepositoryImpl
import di.PerLogin
import domain.repo.EventRepository
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @PerLogin
    fun provideEventRepository(eventApi: EventApi): EventRepository {
        return EventRepositoryImpl(eventApi)
    }
}