import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android) version "1.9.23"
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "dev.nonoxy.weather.report"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.nonoxy.weather.report"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "CITIES_API_BASE_URL",
            "\"https://gist.githubusercontent.com/Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/\""
        )

        buildConfigField(
            "String",
            "WEATHER_API_BASE_URL",
            "\"https://api.openweathermap.org/\""
        )

        val localProperties = Properties()
        localProperties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField(
            "String",
            "WEATHER_API_KEY",
            "\"${localProperties.getProperty("WEATHER_API_KEY")}\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.navigation)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.okhttp.logging.interceptor)

    implementation(project(":weather-api"))
    implementation(project(":weather-data"))
    implementation(project(":features:weather-theme"))
    implementation(project(":features:weather-cities"))
    implementation(project(":features:weather-report"))
}
