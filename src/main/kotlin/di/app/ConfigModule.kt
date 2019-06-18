package di.app

import dagger.Module
import dagger.Provides
import data.api.BaseUrl
import di.ImageFolder

@Module
class ConfigModule {

    @Provides
    fun provideBaseUrl(): BaseUrl {
        return BaseUrl(
            "https://test-auth.tmup.com",
            "https://test-ev.tmup.com",
            "https://test-edge.tmup.com",
            "https://test-file.tmup.com"
        )
    }

    @Provides
    fun provideAuthFilePath(): String {
        return "src/main/resources/auth/teamup_auth.json"
    }

    @Provides
    @ImageFolder
    fun provideImageFolder(): String {
        return "src/main/resources/image"
    }
}