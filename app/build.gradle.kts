plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    //alias(libs.plugins.kotlinParcelize)

}

android {
    namespace = "com.sample.chaitanyasampleapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sample.chaitanyasampleapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"538c416021af406d9d75dc4c04c93267\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"538c416021af406d9d75dc4c04c93267\"")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            resources { excludes += "/META-INF/*" } }
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.mockk)
    implementation(libs.turbine)
    implementation(libs.coroutines.test)
    implementation(libs.kotest.assertions)


    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.dagger.hilt.android)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.androidx.junit.ktx) // Hilt Navigation Compose

    ksp(libs.dagger.compiler)
    ksp(libs.dagger.hilt.android.compiler)
    /*Retrofit and Gson*/
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)

    /* Coroutines for Asynchronous Programming*/
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

     /* Android Testing Libraries*/
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    /* Coin*/
    implementation(libs.coil.compose)
    implementation(libs.androidx.ui)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}