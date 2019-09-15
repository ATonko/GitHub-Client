package tonko.com.client.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiHolder
{
    private const val URL_BASE = "https://github.com"
    private const val API_URL_BASE = "https://api.github.com"


    private val client: OkHttpClient by lazy {
        return@lazy OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    private val baseBuilder: Retrofit.Builder by lazy {
        return@lazy Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    val publicApi: ApiMethods by lazy {
        baseBuilder
                .baseUrl(URL_BASE)
                .build()
                .create(ApiMethods::class.java)

    }
    val privateApi: ApiMethods by lazy {
        baseBuilder
                .baseUrl(API_URL_BASE)
                .build()
                .create(ApiMethods::class.java)

    }
}