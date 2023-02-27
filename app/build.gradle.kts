import kotlin.collections.*
import java.util.Properties
import java.io.FileInputStream

// Create a variable called keystorePropertiesFile, and initialize it to your
// keystore.properties file, in the rootProject folder.
val keystorePropertiesFile = rootProject.file("keystore.properties")

// Initialize a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Load your keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

plugins {
    id("com.android.application")
    id("kotlin-android") //kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion = BuildConfig.compileSdk
    buildToolsVersion = BuildConfig.buildToolsVersion

    defaultConfig {
        applicationId = BuildConfig.applicationId
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = BuildConfig.testInstrumentationRunner

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
                argument("dagger.hilt.disableModulesHaveInstallInCheck", "true")
            }
        }

        ndk {
            //不配置则默认构建并打包所有可用的ABI
            //same with gradle-> abiFilters 'x86_64','armeabi-v7a','arm64-v8a'
            abiFilters.addAll(arrayListOf("x86_64", "armeabi-v7a", "arm64-v8a"))
        }

        base {
            //打包名称示例：BuildSrc(1.2)-release.apk
            archivesName.set("vvvvv_${BuildConfig.versionName}")
            distsDirectory.set(layout.buildDirectory.dir("custom-dist"))
            libsDirectory.set(layout.buildDirectory.dir("custom-libs"))
        }

        signingConfigs {
            getByName("debug") {
                keyAlias = "debug"
                keyPassword = "my debug key password"
                storeFile = file("keystore.jks")
                storePassword = "my keystore password"
            }
            create("release") {
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
            }
        }

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
    packagingOptions {
        resources.excludes += "META-INF/gradle/incremental.annotation.processors"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs= listOf("-Xopt-in=kotlin.time.ExperimentalTime","-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))

    implementation(project(":baselibrary"))
//    implementation(project(":player")) //exoplayer播放器

    implementation(AndroidX.appcompat)
    implementation(AndroidX.preference)
    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.cardview)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.swiperefreshlayout)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.Paging.runtime)
    implementation(AndroidX.Paging.runtimeKtx)

    implementation(ThirdPart.Retrofit.retrofit)
    implementation(ThirdPart.Retrofit.convertGson)

    //material包
    implementation(Google.material)

    // binding
    implementation(ThirdPart.bindables) {
        exclude("com.google.android.material")
    }

    implementation(ThirdPart.whatif)
    implementation(ThirdPart.bundler)
    implementation(ThirdPart.transformation)


    // startup
    implementation(AndroidX.startup)

    //JetPack navigation
    implementation(AndroidX.Navigation.fragmentKtx)
    implementation(AndroidX.Navigation.uiKtx)

    implementation(ThirdPart.immersionBar)
    implementation(ThirdPart.immersionBarKtx)

    //JetPack lifecycle
    implementation(AndroidX.Lifecycle.liveDataKtx)
    implementation(AndroidX.Lifecycle.viewModelKtx)
    implementation(AndroidX.Lifecycle.viewModelSavedState)
    implementation(AndroidX.Lifecycle.commonJava8)
    implementation(AndroidX.Lifecycle.service)
    implementation(AndroidX.Lifecycle.runtimeKtx)

    //Hilt
    implementation(AndroidX.Hilt.common)
    kapt(AndroidX.Hilt.dagger_hilt_compiler)
    kapt(AndroidX.Hilt.hilt_hilt_compiler)


    //JetPack Room
    implementation(AndroidX.Room.runtime)
    kapt(AndroidX.Room.compiler)
    implementation(AndroidX.Room.ktx)


    //viewPager
    implementation(AndroidX.ViewPager.viewpager2)
    implementation(AndroidX.ViewPager.viewpager)

    //okhttp
    implementation(ThirdPart.OkHttp.okhttp)
    implementation(ThirdPart.OkHttp.urlConnection)
    implementation(ThirdPart.OkHttp.loggingInterceptor)

    //mmkv
//    implementation(ThirdPart.mmkv)

    //third views
    implementation(ThirdPart.smoothBottomBar)
    implementation(ThirdPart.spinKit)
    implementation(ThirdPart.multiType)
    implementation(ThirdPart.fullDraggableDrawer)
//    implementation(ThirdPart.autoSize)

    //exoplayer media库
    implementation("com.github.JarvanMo:ExoVideoView:2.1.6")


    //Android开源弹幕引擎·烈焰弹幕使 ～ bilibili
    implementation(ThirdPart.danmakuFlameMaster)
    implementation(ThirdPart.danMakuNdkVersion)

    // debugging
    implementation(ThirdPart.timber)

    // unit test
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)

}