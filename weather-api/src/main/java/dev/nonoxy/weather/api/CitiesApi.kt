package dev.nonoxy.weather.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.nonoxy.weather.api.models.CityDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

interface CitiesApi {

    @GET("cities.json")
    suspend fun getCities(): Result<List<CityDTO>>
}

fun CitiesApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
): CitiesApi {
    return retrofit(baseUrl, okHttpClient).create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
): Retrofit {

    val jsonConverterFactory = Json.asConverterFactory("application/json".toMediaType())
    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(jsonConverterFactory)
        .client(modifiedOkHttpClient)
        .build()
}