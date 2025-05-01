plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ucucite.handypro_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ucucite.handypro_app"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.livedata)
    implementation(libs.material)
    implementation(libs.recyclerview)
    implementation(libs.viewpager2)

    implementation(libs.room.runtime)
    implementation(libs.room.common)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)

    implementation(libs.lombok)
    annotationProcessor(libs.lombok)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
