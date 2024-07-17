@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.lnf.evraztest1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lnf.evraztest1"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.1"

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
        buildConfig = true
        viewBinding = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

configurations {
    all {
        resolutionStrategy {
            force("io.insert-koin:koin-android:3.5.6")
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.navigation.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.ui.tooling)


    //implementation(libs.koin.android.scope)
    //implementation(libs.koin.core)
    runtimeOnly("io.insert-koin:koin-core:3.5.6")

    implementation(libs.koin.android)
    //implementation(libs.koin.android.viewmodel)
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler) //ksp не завёлся почему-то, надо разобраться
    implementation(libs.androidx.room.ktx)


    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.material3)

}