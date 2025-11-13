plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.doubaomini"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.doubaomini"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    dataBinding {
        enable = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Retrofit 核心库
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    // Gson 解析适配器（将响应转为实体类）
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    // OkHttp 日志拦截器（可选，调试用）
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}