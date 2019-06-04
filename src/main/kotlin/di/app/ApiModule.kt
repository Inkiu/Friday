package di.app

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import data.api.BaseUrl
import data.api.OAuthApi

@Module
class ApiModule {

    @Provides
    fun provideAuthApi(baseUrl: BaseUrl): OAuthApi {
        return createRetrofit(baseUrl = baseUrl.auth, timeout = 10L).create(OAuthApi::class.java)
    }

    private fun createRetrofit(baseUrl: String, timeout: Long): Retrofit {
        val logging = HttpLoggingInterceptor {
            println("Retrofit : $it")
        }.apply { level = HttpLoggingInterceptor.Level.BASIC }

        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClientBuilder.build())
            .build()
    }

}