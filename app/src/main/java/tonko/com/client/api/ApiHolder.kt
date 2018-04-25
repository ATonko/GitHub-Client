package tonko.com.client.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiHolder {
    private val URL_BASE = "https://github.com"
    private val API_URL_BASE = "https://api.github.com"


    val publicApi: PublicApi by lazy {
        val client = OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
        Retrofit.Builder()
                .client(client)
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PublicApi::class.java)

    }
    val privateApi: PublicApi by lazy {
        val client = OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
        Retrofit.Builder()
                .client(client)
                .baseUrl(API_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PublicApi::class.java)

    }
}