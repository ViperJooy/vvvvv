plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = BuildConfig.targetSdkVersion

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "com.viper.player"
}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.coordinatorlayout)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.Lifecycle.viewModelKtx)

//    implementation(ThirdPart.ExoPlayerMedia.all)
//    implementation(ThirdPart.ExoPlayerMedia.ui)
    implementation(ThirdPart.ExoPlayer.all)


    implementation(AndroidX.Hilt.common)
    kapt(AndroidX.Hilt.dagger_hilt_compiler)
    kapt(AndroidX.Hilt.hilt_hilt_compiler)
}