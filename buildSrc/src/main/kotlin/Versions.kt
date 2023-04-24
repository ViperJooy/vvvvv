object Versions {

    object AndroidX {
//        const val appCompat = "1.4.0-alpha01"
        const val appCompat = "1.6.1"
        const val preference = "1.2.0"
//        const val coreKtx = "1.7.0-alpha01"
        const val coreKtx = "1.9.0"
//        const val activityKtx = "1.3.1"
        const val activityKtx = "1.6.1"
//        const val fragmentKtx = "1.4.0-alpha04"
        const val fragmentKtx = "1.5.5"
//        const val constraintLayout = "2.1.0"
        const val constraintLayout = "2.1.4"
        const val coordinatorLayout = "1.2.0"
        const val cardView = "1.0.0"
        const val recyclerView = "1.2.1"
        const val swipeRefreshLayout = "1.2.0-alpha01"

        const val legacySupportV4 = "1.0.0"
        const val multidex = "2.0.1"
        const val vectorDrawable = "1.1.0"
        const val concurrentFuturesKtx = "1.1.0"

        object Compose {
            const val version = "1.3.3"
            const val material = "1.3.1"
            const val material3 = "1.0.1"
            const val activity = "1.6.1"
        }

        object ViewPager {
            const val viewpager = "1.0.0"

            //viewpager2
            const val viewpager2 = "1.1.0-beta01"
        }

        object Paging {
            const val version = "3.1.1"
            const val compose = "1.0.0-alpha18"
        }

        object Hilt {
//            const val hiltCoreVersion = "2.40.5"
            const val hiltCoreVersion = "2.44"
            const val hiltVersion = "1.0.0"
        }

        const val startup = "1.1.1"


        object Lifecycle {
            const val version = "2.5.1"

//            @Deprecated("lifecycle-extensions 已弃用，截至到目前最后一个版本2.2.0，ViewModelProviders.of()被废弃了，使用ViewModelProvider(ViewModelStoreOwner)")
//            const val extensions = "2.2.0"
        }

        object Navigation {
            //这个版本支持多返回栈了
            const val version = "2.5.3"
        }

        object Room {
            /**
             * Fixed an issue with Room’s SQLite native library to support Apple’s M1 chips.
             * Change Version to 2.4.0-alpha03 or above
             */
            const val version = "2.5.0"
        }

        object Camera {
            const val version = "1.2.1"
        }
    }

    object Google {
        const val material = "1.8.0"
        const val gson = "2.10.1"
        const val barcode_scanning = "17.0.3"
    }

    object Gradle {
        const val gradle_version = "7.4.2"
    }

    object Kotlin {
//        const val kotlin_version = "1.6.21"
        const val kotlin_version = "1.8.10"
//        const val coroutines_version = "1.5.2"
        const val coroutines_version = "1.6.4"
    }

    object Testing {
        const val junit = "4.13.2"
        const val androidJunit = "1.1.2"
        const val androidRunner = "1.4.0"
        const val espresso = "3.4.0"
    }


    object ThirdPart {
        const val retrofit = "2.9.0"
        const val okHttp = "4.10.0"
        const val glide = "4.13.2"
//        const val coil = "1.4.0"
        const val coil = "2.2.2"

        object Skydoves{
            const val bindables = "1.1.0"
            const val whatif = "1.1.2"
            const val baseAdapter = "1.0.4"
            const val bundler = "1.0.4"
            const val sandwich = "1.3.3"
            const val transformation = "1.1.1"
        }


        const val exoPlayer = "2.18.4"
        const val exoPlayerMedia = "1.0.0-beta01"

        const val materialDialogs = "3.3.0"
        const val bannerVp = "3.1.5"
        const val smoothBottomBar = "1.7.9"

        const val immersionBar = "3.0.0"
        const val immersionBarKtx = "3.0.0"

        const val refreshLayoutKernel = "2.0.1"
        const val refreshHeader = "2.0.1"

        const val baseRecycleViewHelper = "3.0.4"

        const val xRecycleView = "1.3.2"

        const val magicIndicator = "1.7.0"

        const val autoSize = "1.2.1"

        const val loadSir = "1.3.8"

        const val backgroundLibrary = "1.6.5"

        const val console = "1.2.0"

        const val lottie = "4.1.0"

        const val spinKit = "1.4.0"

        const val multiType = "4.3.0"

        const val fullDraggableDrawer = "1.0.3"

        const val danmakuFlameMaster = "0.9.25"
        const val danMakuNdkVersion = "0.9.21"


        const val koin_version = "2.1.5"
        const val dagger_version = "2.45"

        const val permissionX = "1.7.1"

        const val utilCodex = "1.31.1"

        const val mmkv = "1.2.15"

        const val bugly = "4.0.4"

        const val leakcanary = "2.10"

        const val rxAndroidBle = "1.12.1"

        const val rxjavaReplayingShare = "2.2.0"

        const val aRouter = "1.5.2"
        const val aRouterCompiler = "1.5.2"

        const val iconCore = "5.4.0"

        const val timber = "5.0.1"

    }


}