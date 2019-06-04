package di.app

import app.App
import dagger.Module
import dagger.Provides
import data.api.OAuthApi
import presentation.LoginService

@Module
class AppModule(
    private val app: App
) {
    @Provides
    fun provideLoginService(oauthApi: OAuthApi, authFilePath: String): LoginService {
        return LoginService(oauthApi = oauthApi, authFilePath = authFilePath)
    }

    @Provides
    fun provideApp(): App {
        return app
    }
}