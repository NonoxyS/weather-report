# Тестовое задание для Газпром-Медиа

Приложения показывает простой список городов, с возможностью посмотреть текущую температуру в каждом из них с помошью OpenWeatherMap API

### Сборка приложения
> [!IMPORTANT]
> Нужно добавить API ключ OpenWeatherMap API в файл **./local.properties**

```kotlin
WEATHER_API_KEY=api_key_here
```

### Технологический стек:
- Android SDK
- [Android Jetpack](https://developer.android.com/jetpack)
- [KotlinX Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/)
- [Jetpack Compose](https://developer.android.com/develop/ui/compose)
- [Hilt](https://dagger.dev/hilt/)
- [MD3 Compose](https://developer.android.com/develop/ui/compose/designsystems/material3)

### Основные модули
- weather-api - модуль для работы с сетевыми запросами
- weather-data - модуль для работы с данными
- features:* - все фичи приложения
- app - сборка приложения
