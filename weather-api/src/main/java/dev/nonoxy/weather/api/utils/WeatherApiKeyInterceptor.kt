package dev.nonoxy.weather.api.utils

import okhttp3.Interceptor
import okhttp3.Response

internal class WeatherApiKeyInterceptor(private val weatherApiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("appid", weatherApiKey)
            .build()

        return chain.proceed(
            chain.request().newBuilder()
                .url(newUrl)
                .build()
        )
    }
}