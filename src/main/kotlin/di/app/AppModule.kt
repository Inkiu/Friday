package di.app

import app.App
import dagger.Module
import dagger.Provides
import data.api.EventConfigApi
import data.api.OAuthApi
import presentation.login.LoginService
import javax.inject.Singleton

@Module
class AppModule(
    private val app: App
) {
    @Provides
    @Singleton
    fun provideLoginService(oauthApi: OAuthApi, eventConfigApi: EventConfigApi, authFilePath: String): LoginService {
        return LoginService(oauthApi = oauthApi, eventApi = eventConfigApi, authFilePath = authFilePath)
    }

    @Provides
    fun provideApp(): App {
        return app
    }
}