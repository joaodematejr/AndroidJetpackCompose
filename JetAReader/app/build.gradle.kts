plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}

android {
    namespace = "com.demate.jetareader"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demate.jetareader"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    //firebase auth and firestore
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    //kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    //material icons - use with caution!
    //implementation(libs.androidx.material.icons.extended)
    // Coroutines
    //implementation(libs.kotlinx.coroutines.android)
    //implementation(libs.kotlinx.coroutines.core)
    //implementation(libs.kotlinx.coroutines.play.services)
    // Coroutine Lifecycle Scopes
    //implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //lifecycle
    //implementation(libs.androidx.lifecycle.runtime.ktx)
    // Coil
    implementation(libs.coil.compose)
    // Retrofit
    implementation(libs.retrofit)
    // OkHttp
    implementation(libs.okhttp)
    // JSON Converter
    implementation(libs.converter.gson)
}

kapt {
    correctErrorTypes = true
}