package dev.nonoxy.weather.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.nonoxy.weather.api.models.WeatherDTO
import dev.nonoxy.weather.api.utils.WeatherApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    /**
     * Details [here](https://openweathermap.org/current)
     */
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String = "metric",
    ): Result<WeatherDTO>
}

fun WeatherApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
): WeatherApi {
    return retrofit(baseUrl, apiKey, okHttpClient).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?
): Retrofit {
    val json = Json { ignoreUnknownKeys = true }
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(WeatherApiKeyInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(jsonConverterFactory)
        .client(modifiedOkHttpClient)
        .build()
}