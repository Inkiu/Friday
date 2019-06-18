package di.event

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import data.EventConfigProvider
import data.TokenAuthenticator
import data.api.*
import di.PerLogin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @PerLogin
    fun provideAuthApi(baseUrl: BaseUrl, tokenAuthenticator: TokenAuthenticator): AuthApi {
        return createRetrofit(
            baseUrl = baseUrl.auth,
            timeout = 10L,
            authenticator =  tokenAuthenticator
            ).create(AuthApi::class.java)
    }

    @Provides
    @PerLogin
    fun provideEdgeApi(baseUrl: BaseUrl, tokenAuthenticator: TokenAuthenticator): EdgeApi {
        return createRetrofit(
            baseUrl = baseUrl.edge,
            timeout = 10L,
            authenticator =  tokenAuthenticator
        ).create(EdgeApi::class.java)
    }

    @Provides
    @PerLogin
    fun provideEventApi(baseUrl: BaseUrl,
                        eventConfigProvider: EventConfigProvider,
                        tokenAuthenticator: TokenAuthenticator): EventApi {
        return createRetrofit(
            baseUrl = baseUrl.event,
            timeout = (eventConfigProvider.getEventWaitTime()?.toLong() ?: 30L) + 5L,
            authenticator =  tokenAuthenticator
        ).create(EventApi::class.java)
    }


    @Provides
    @PerLogin
    fun provideFileApi(baseUrl: BaseUrl, tokenAuthenticator: TokenAuthenticator): FileApi {
        return createRetrofit(
            baseUrl = baseUrl.file,
            timeout = 20L,
            authenticator = tokenAuthenticator,
            logLevel = HttpLoggingInterceptor.Level.HEADERS
        ).create(FileApi::class.java)
    }

    private fun createRetrofit(baseUrl: String,
                               timeout: Long,
                               authenticator: TokenAuthenticator,
                               logLevel: HttpLoggingInterceptor.Level? = null
    ): Retrofit {
        val logging = HttpLoggingInterceptor {
            println("Retrofit : $it")
        }.apply { level = logLevel ?: HttpLoggingInterceptor.Level.BODY }

        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authenticator)
            .authenticator(authenticator)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClientBuilder.build())
            .build()
    }
}