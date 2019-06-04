import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import data.api.AuthApi
import data.api.OAuthApi
import data.repo.AuthRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun main() {
    val retrofit = createRetrofit("https://test-auth.tmup.com", 10)
    val oauthApi = retrofit.create(OAuthApi::class.java)
    val authApi = retrofit.create(AuthApi::class.java)

    val authRepository = AuthRepository(oauthApi, authApi)

    val context = Dispatchers.Default + Job() + CoroutineExceptionHandler { _, e -> e.printStackTrace() }
    val token = runBlocking(context) {
        authRepository.botLogin()
    }

    println(token)
}

fun createRetrofit(baseUrl: String, timeout: Long): Retrofit {
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