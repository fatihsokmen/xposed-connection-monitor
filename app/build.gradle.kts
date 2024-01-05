@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.github.fatihsokmen.connectionmonitor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.fatihsokmen.connectionmonitor"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly(libs.xposed.api)
    compileOnly(libs.volley)
    compileOnly(libs.okhttp)

    implementation(libs.koin.core)
    implementation(libs.material)

    testImplementation(libs.junit)
    testImplementation(libs.xposed.api)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}