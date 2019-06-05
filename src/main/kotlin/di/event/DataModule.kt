package di.event

import dagger.Binds
import dagger.Module
import dagger.Provides
import data.api.EdgeApi
import data.api.EventApi
import data.repo.ChatRepositoryImpl
import data.repo.EventRepositoryImpl
import di.PerLogin
import domain.repo.ChatRepository
import domain.repo.EventRepository
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @PerLogin
    fun provideEventRepository(eventApi: EventApi): EventRepository {
        return EventRepositoryImpl(eventApi)
    }

    @Provides
    @PerLogin
    fun provideChatRepository(edgeApi: EdgeApi): ChatRepository {
        return ChatRepositoryImpl(edgeApi)
    }
}