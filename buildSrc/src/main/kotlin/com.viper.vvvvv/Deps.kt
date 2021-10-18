package com.viper.vvvvv

/**
 * author: viper
 * date: 2021-08-27 3:43 下午
 * desc: Deps
 * /**
 * 版本信息
*/
object Versions {
const val minSdk = 21
const val targetSdk = 30
const val compileSdk = 30
const val buildTool = "30.0.2"
}

/**
 * 依赖库路径
*/
object Deps {
//kotlin
const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"
const val koinAndroid = "org.koin:koin-android:2.2.2"
const val koinAndroidxScope = "org.koin:koin-androidx-scope:2.2.2"
const val koinAndroidxViewmodel = "org.koin:koin-androidx-viewmodel:2.2.2"

//anko
const val anko = "org.jetbrains.anko:anko:0.10.8"
const val ankoCommon = "org.jetbrains.anko:anko-common:0.10.8"
const val kotlinStdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.10"

//androidx
const val appcompat = "androidx.appcompat:appcompat:1.2.0"
const val design = "com.google.android.material:material:1.2.1"
const val cardview = "androidx.cardview:cardview:1.0.0"
const val annotationX = "androidx.annotation:annotation:1.1.0"
const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
const val room = "androidx.room:room-runtime:2.2.6"
const val roomKtx = "androidx.room:room-ktx:2.2.6"
const val roomCompiler = "androidx.room:room-compiler:2.2.6"
const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
const val lifecycleViewmodelSavedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0"
const val coreKtx = "androidx.core:core-ktx:1.3.2"
const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.5"
const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"

//network
const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
const val retrofitAdapterRxjava = "com.squareup.retrofit2:adapter-rxjava:2.9.0"
const val retrofitAdapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:2.9.0"
const val okhttp = "com.squareup.okhttp3:okhttp:4.9.1"
const val glide = "com.github.bumptech.glide:glide:4.12.0"
const val glideCompiler = "com.github.bumptech.glide:compiler:4.12.0"
const val okhttputils = "com.zhy:okhttputils:2.6.2"
const val picasso = "com.squareup.picasso:picasso:2.8"

//view
const val autosize = "me.jessyan:autosize:1.2.1"
const val baseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.6"
const val titlebar = "com.wuhenzhizao:titlebar:1.2.0"
const val smartShowAll = "com.github.the-pig-of-jungle.smart-show:all:3.0.3"
const val smartRefreshLayoutKernel = "com.scwang.smart:refresh-layout-kernel:2.0.3"
const val smartRefreshHeaderClassics = "com.scwang.smart:refresh-header-classics:2.0.3"
const val lottie = "com.airbnb.android:lottie:3.6.0"
const val loadsir = "com.kingja.loadsir:loadsir:1.3.8"
const val photoview = "com.github.chrisbanes.photoview:library:1.2.3"
const val tbssdk = "com.tencent.tbs.tbssdk:sdk:43986"
const val xUpdate = "com.github.xuexiangjys:XUpdate:2.0.7"
const val pictureSelector = "com.github.LuckSiege.PictureSelector:picture_library:v2.6.0"
const val bottomNavigationViewEx = "com.github.ittianyu:BottomNavigationViewEx:2.0.4"
const val vlayout = "com.alibaba.android:vlayout:1.3.0@aar"
const val ycbannerlib = "cn.yc:YCBannerLib:1.4.0"
const val bgaQrcode = "cn.bingoogolapple:bga-qrcode-zxing:1.3.7"
const val doodle = "com.github.1993hzw:Doodle:5.5.4"
const val flowlayout = "com.hyman:flowlayout-lib:1.1.2"
const val lfilepickerlibrary = "com.leon:lfilepickerlibrary:1.8.0"
const val easyFloat = "com.github.princekin-f:EasyFloat:1.3.4"
const val clansFab = "com.github.clans:fab:1.6.4"
const val andRatingBar = "com.github.giswangsj:AndRatingBar:1.0.5"
const val androidPdfViewer = "com.github.barteksc:android-pdf-viewer:2.8.2"
const val playerbase = "com.kk.taurus.playerbase:playerbase:3.4.2"
const val dropDownMenu = "com.wdeo3601:drop-down-menu:1.0.5"
const val labelsView = "com.github.donkingliang:LabelsView:1.6.3"
const val circleProgressbar = "com.dinuscxj:circleprogressbar:1.3.6"
const val rvAdapter = "com.gitee.cbfg5210:RVAdapter:0.3.5"

//rx2
const val rxjava2 = "io.reactivex.rxjava2:rxjava:2.2.20"
const val rxandroid2 = "io.reactivex.rxjava2:rxandroid:2.1.1"
const val rxlifecycle2 = "com.trello.rxlifecycle2:rxlifecycle:2.2.2"
const val rxlifecycle2Android = "com.trello.rxlifecycle2:rxlifecycle-android:2.2.2"
const val rxlifecycle2Components = "com.trello.rxlifecycle2:rxlifecycle-components:2.2.2"
const val rxlifecycle2AndroidLifecycle = "com.trello.rxlifecycle2:rxlifecycle-android-lifecycle:2.2.2"
const val rxcache2 = "com.github.VictorAlbertos.RxCache:runtime:1.8.3-2.x"
const val rxpermissions2 = "com.github.tbruyelle:rxpermissions:0.11"
const val rxerrorhandler2 = "me.jessyan:rxerrorhandler:2.1.1"

//tools
const val dagger2 = "com.google.dagger:dagger:2.31.2"
const val dagger2Compiler = "com.google.dagger:dagger-compiler:2.31.2"
const val gson = "com.google.code.gson:gson:2.8.6"
const val multidex = "androidx.multidex:multidex:2.0.1"
const val javaxAnnotation = "javax.annotation:jsr250-api:1.0"
const val arouterApi = "com.alibaba:arouter-api:1.5.1"
const val arouterCompiler = "com.alibaba:arouter-compiler:1.5.1"
const val progressmanager = "me.jessyan:progressmanager:1.5.0"
const val retrofitUrlManager = "me.jessyan:retrofit-url-manager:1.4.0"
const val liveEventBusX = "com.jeremyliao:live-event-bus-x:1.7.3"
const val jolyglotGson = "com.github.VictorAlbertos.Jolyglot:gson:0.0.6"
const val timber = "com.jakewharton.timber:timber:4.7.1"
const val umengCommon = "com.umeng.umsdk:common:2.1.0"
const val umengUtdid = "com.umeng.umsdk:utdid:1.1.5.3"
const val umengAnalytics = "com.umeng.umsdk:analytics:8.1.3"
const val zlwAudioRecorder = "com.github.zhaolewei:ZlwAudioRecorder:1.07"
const val nineoldandroids = "com.nineoldandroids:library:2.4.0"
const val butterknife = "com.jakewharton:butterknife:10.2.3"
const val butterknifeCompiler = "com.jakewharton:butterknife-compiler:10.2.3"
const val eventbus = "org.greenrobot:eventbus:3.2.0"
const val mmkvStatic = "com.tencent:mmkv-static:1.2.7"
const val greendao = "org.greenrobot:greendao:3.3.0"
const val filedownloader = "com.liulishuo.filedownloader:library:1.7.7"
const val smartkey = "com.github.Vove7.SmartKey:smartkey:6.3.3"
const val immersionbar = "com.gyf.immersionbar:immersionbar:3.0.0"
const val viewbindingKtx = "com.dylanc:viewbinding-ktx:1.1.1"
const val viewbindingBaseKtx = "com.dylanc:viewbinding-base-ktx:1.1.1"
const val viewbindingBrvahKtx = "com.dylanc:viewbinding-brvah-ktx:1.1.1"

//test
const val junit = "junit:junit:4.+"
const val extJunit = "androidx.test.ext:junit:1.1.2"
const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
const val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
const val spidermanDebug = "com.simple:spiderman:1.1.7"
const val spidermanRelease = "com.simple:spiderman-no-op:1.1.5"
const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.5"
}
 */

object Versions {
    const val applicationId = "com.viper.vvvvv"
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"

    /**
     * gradle plugins
     */
    const val gradleBuildTool = "4.1.3"
    const val spotlessGradle = "5.14.0"
    const val versionPlugin = "0.28.0"
    const val ktlint = "0.40.0"

    /**
     * kotlin
     */
    const val kotlin = "1.5.21"

    /**
     * androidx
     */
    const val appcompat = "1.2.0"
    const val ktx = "1.3.1"
    const val materialVersion = "1.2.1"
    const val constraintVersion = "2.0.4"
    const val preferenceKtxVersion = "1.1.1"

    /**
     * architecture components
     */
    const val fragmentVersion = "1.3.0"
    const val lifecycleVersion = "2.2.0"
    const val roomVersion = "2.4.0-alpha04"
    const val archCompomentVersion = "2.1.0"

    /**
     * binding
     */
    const val bindablesVersion = "1.0.9"

    /**
     * startup
     */
    const val startupVersion = "1.1.0"

    /**
     * di
     */
    const val hiltCoreVersion = "2.38.1"
    const val hiltVersion = "1.0.0"

    /**
     * network
     */
    const val retrofitVersion = "2.9.0"
    const val okhttpVersion = "4.9.1"
    const val chuckerVersion = "3.5.2"
    const val sandwichVersion = "1.2.1"

    /**
     * moshi
     */
    const val moshiVersion = "1.11.0"

    /**
     * coroutines
     */
    const val coroutinesVersion = "1.5.0"

    /**
     * whatIf
     */
    const val whatIfVersion = "1.1.0"

    /**
     * glide
     */
    const val glideVersion = "4.12.0"
    const val glidePaletteVersion = "2.1.2"

    /**
     * bundler
     */
    const val bundlerVersion = "1.0.4"

    /**
     * transformation
     */
    const val transformationLayout = "1.0.4"

    /**
     * adapter
     */
    const val recyclerView = "1.2.1"
    const val baseAdapter = "1.0.4"

    /**
     * android ribbon
     */
    const val androidRibbonVersion = "1.0.3"

    /**
     * progress view
     */
    const val progressViewVersion = "1.1.1"

    /**
     * rainbow
     */
    const val rainbowVersion = "1.0.3"

    /**
     * debugging
     */
    const val timberVersion = "5.0.0"

    /**
     * mmkv
     */
    const val mmkv = "1.2.10"

    /**
     * test
     */
    const val junit = "4.+"
    const val extJunit = "1.1.2"
    const val espressoCore = "3.3.0"
    const val leakcanary = "2.5"

}


object Deps {
    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
        const val lifecycleLivedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
        const val lifecycleViewmodelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        const val lifecycleViewmodelSavedstate =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"
        const val preferenceKtx =
            "androidx.preference:preference-ktx:${Versions.preferenceKtxVersion}"
    }


    /**
     * network
     */
    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val retrofitConverterGson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val retrofitAdapterRxjava =
            "com.squareup.retrofit2:adapter-rxjava:${Versions.retrofitVersion}"
        const val retrofitAdapterRxjava2 =
            "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
        const val chuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}"
        const val chuckerRelease =
            "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}"
    }

    /**
     * tools
     */
    object Tools {
        const val mmkv = "com.tencent:mmkv-static:${Versions.mmkv}"
    }

    /**
     * test
     */
    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
        const val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    }

}
