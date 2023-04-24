/**
 * @author viper
 * ·不带 ktx 后缀的为 java 依赖，核心功能在此库
 * ·带 ktx 后缀为 kotlin 依赖，提供很多方便的扩展函数, ktx 默认引入不带 ktx 的库
 * 依赖关系可以使用：【 gradlew :app:dependencies --scan --configuration releaseRuntimeClasspath >dependenciesTree.txt 输出app模块依赖树 】
 * 查询包的历史版本：https://androidx.tech/artifacts/appcompat/appcompat/
 * support包和androidx包映射关系查询：https://developer.android.com/jetpack/androidx/migrate/artifact-mappings
 */
@Suppress("SpellCheckingInspection")
object AndroidX {
    /**
     * appcompat中默认引入了很多库(比如activity库、fragment库、core库、annotation库、drawerlayout库、appcompat-resources)
     * 如果想使用其中某个库的更新版本，可以单独引用，比如下面的vectordrawable
     * 提示：对于声明式依赖，同一个库的不同版本，gradle会自动使用最新版本来进行依赖替换、编译
     */
    const val appcompat         = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"

    const val preference        = "androidx.preference:preference:${Versions.AndroidX.preference}"

    //core包+ktx扩展函数
    const val coreKtx           = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"

    //activity+ktx扩展函数
    const val activityKtx       = "androidx.activity:activity-ktx:${Versions.AndroidX.activityKtx}"

    //fragment+ktx扩展函数
    const val fragmentKtx       = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragmentKtx}"

    //约束布局
    const val constraintlayout  = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"

    const val coordinatorlayout  = "androidx.coordinatorlayout:coordinatorlayout:${Versions.AndroidX.coordinatorLayout}"

    //卡片控件
    const val cardview          = "androidx.cardview:cardview:${Versions.AndroidX.cardView}"

    //recyclerView
    const val recyclerView      = "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"

    //swiperefreshlayout
    const val swiperefreshlayout= "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefreshLayout}"

    object Compose{
        const val ui            = "androidx.compose.ui:ui:${Versions.AndroidX.Compose.version}"
        const val material      ="androidx.compose.material:material:${Versions.AndroidX.Compose.material}"
        const val preview       ="androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.Compose.version}"
        //use debugImplementation
        const val uiTooling     ="androidx.compose.ui:ui-tooling:$${Versions.AndroidX.Compose.version}"
        const val material3     ="androidx.compose.material3:material3:${Versions.AndroidX.Compose.material3}"
        const val activity      ="androidx.activity:activity-compose:${Versions.AndroidX.Compose.activity}"

        //更多compose工具集查看 accompanist项目#https://github.com/google/accompanist
    }

    object ViewPager {
        //viewpager
        const val viewpager     = "androidx.viewpager:viewpager:${Versions.AndroidX.ViewPager.viewpager}"

        //viewpager2
        const val viewpager2    = "androidx.viewpager2:viewpager2:${Versions.AndroidX.ViewPager.viewpager2}"
    }

    object Paging {
        private const val version = Versions.AndroidX.Paging.version
        const val runtime       = "androidx.paging:paging-runtime:$version"
        const val runtimeKtx    = "androidx.paging:paging-runtime-ktx:$version"

        // optional - RxJava2 support
        const val rxjava2       = "androidx.paging:paging-rxjava2:$version"
        const val rxjava2Ktx    = "androidx.paging:paging-rxjava2-ktx:$version"

        // optional - RxJava3 support
        const val rxjava3       = "androidx.paging:paging-rxjava3:$version"

        // optional - Guava ListenableFuture support
        const val guava         = "androidx.paging:paging-guava:$version"

        // alternatively - without Android dependencies for tests
        const val testingCommon = "androidx.paging:paging-common:$version"
        const val testingCommonKtx = "androidx.paging:paging-common-ktx:$version"

        // optional - Jetpack Compose integration
        const val compose       = "androidx.paging:paging-compose:${Versions.AndroidX.Paging.compose}"
    }

    object Hilt {
        /**
         *  hiltCoreVersion  : '2.40.5',
            hiltVersion      : '1.0.0',
        implementation "com.google.dagger:hilt-android:$versions.hiltCoreVersion"
        kapt "com.google.dagger:hilt-compiler:$versions.hiltCoreVersion"
        kapt "androidx.hilt:hilt-compiler:$versions.hiltVersion"
         */

        private const val version         = Versions.AndroidX.Hilt.hiltCoreVersion
        const val common                  = "com.google.dagger:hilt-android:$version"
        //use kapt
        const val dagger_hilt_compiler    = "com.google.dagger:hilt-compiler:$version"
        const val hilt_hilt_compiler      = "androidx.hilt:hilt-compiler:${Versions.AndroidX.Hilt.hiltVersion}"
        //in build.gralde,classpath("${AndroidX.Hilt.gradle_plugin}")
        const val gradlePlugin            = "com.google.dagger:hilt-android-gradle-plugin:$version"

    }

    const val startup           = "androidx.startup:startup-runtime:${Versions.AndroidX.startup}"

    object Lifecycle {
        private const val version = Versions.AndroidX.Lifecycle.version

        @Deprecated("lifecycle-extensions 已弃用，截至到目前最后一个版本2.2.0，ViewModelProviders.of()被废弃了，使用ViewModelProvider(ViewModelStoreOwner)")
//        const val extensions    = "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.Lifecycle.extensions}"

        const val livedata      = "androidx.lifecycle:lifecycle-livedata:$version"
        const val liveDataKtx   = "androidx.lifecycle:lifecycle-livedata-ktx:$version"

        const val viewModel     = "androidx.lifecycle:lifecycle-viewmodel:$version"
        const val viewModelKtx  = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"

        //Saved state module for ViewModel
        const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"

        //Annotation processor 注释处理器
        //use kapt,not implementation
        const val compiler      = "androidx.lifecycle:lifecycle-compiler:$version"

        // if using Java8, use the following instead of lifecycle-compiler
        //提供了DefaultLifecycleObserver接口
        const val commonJava8   = "androidx.lifecycle:lifecycle-common-java8:$version"

        //helpers for implementing LifecycleOwner in a Service
        const val service       = "androidx.lifecycle:lifecycle-service:$version"

        //ProcessLifecycleOwner provides a lifecycle for the whole application process
        const val process       = "androidx.lifecycle:lifecycle-process:$version"

        const val runtime       = "androidx.lifecycle:lifecycle-runtime:$version"
        const val runtimeKtx    = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
    }


    object Navigation {
        //这个版本支持多返回栈了
        private const val version = Versions.AndroidX.Navigation.version

        //const val fragment = "androidx.navigation:navigation-fragment:$version"
        const val fragmentKtx     = "androidx.navigation:navigation-fragment-ktx:$version"

        //const val ui = "androidx.navigation:navigation-ui:$version"
        const val uiKtx           = "androidx.navigation:navigation-ui-ktx:$version"

        const val safeArgs        = "androidx.navigation:navigation-safe-args-generator:$version"

        //classpath("${AndroidX.Navigation.safeArgsGradlePlugin}")
        const val safeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"

        // Dynamic Feature Module Support
        const val dynamic         = "androidx.navigation:navigation-dynamic-features-fragment:$version"
        const val dynamicRuntime  = "androidx.navigation:navigation-dynamic-features-runtime:$version"

        // Testing Navigation
        const val testing         = "androidx.navigation:navigation-testing:$version"

        //Jetpack Compose Integration
        const val compose         = "androidx.navigation:navigation-compose:$version"
    }


    object Room {
        private const val version = Versions.AndroidX.Room.version

        const val runtime         = "androidx.room:room-runtime:$version"

        // for java use annotationProcessor , for kotlin use kapt
        const val compiler        = "androidx.room:room-compiler:$version"

        // optional - Kotlin Extensions and Coroutines support for Room
        const val ktx             = "androidx.room:room-ktx:$version"

        // optional - RxJava support for Room
        const val rxjava2         = "androidx.room:room-rxjava2:$version"
        const val rxjava3         = "androidx.room:room-rxjava3:$version"

        // optional - Guava support for Room, including Optional and ListenableFuture
        const val guava           = "androidx.room:room-guava:$version"

        //Testing Room
        const val testing         = "androidx.room:room-testing:$version"
    }

    object Camera {
        private const val version = Versions.AndroidX.Camera.version

        const val camera2         = "androidx.camera:camera-camera2:$version"

        const val core            = "androidx.camera:camera-core:$version"

        const val lifecycle       = "androidx.camera:camera-lifecycle:$version"

        const val view            = "androidx.camera:camera-view:1.0.0-alpha27"
    }


    //com.android.support:support-v4的androidx映射版本，关于其他支持库查看{https://developer.android.com/topic/libraries/support-library/packages}
    const val legacySupportV4     = "androidx.legacy:legacy-support-v4:${Versions.AndroidX.legacySupportV4}"

    /**
    multidex分包
    @description:提供了MultiDexApplication，它允许您在Android 4.4和更早的设备上使用不安全的multidex形式。
    21或更高版本默认启用multidex并且您不需要导入multidex库和设置配置文件。
     */
    const val multidex            = "androidx.multidex:multidex:${Versions.AndroidX.multidex}"

    //sdk包下graphics.drawable下有一个VectorDrawable类，对于较高的版本不需要引入此库来支持基于XML矢量图形创建可绘制对象。
    const val vectordrawable      = "androidx.vectordrawable:vectordrawable:${Versions.AndroidX.vectorDrawable}"

    //在kotlin中要添加Futures依赖项，参考：https://developer.android.com/jetpack/androidx/releases/concurrent
    const val concurrentFuturesKtx= "androidx.concurrent:concurrent-futures-ktx:${Versions.AndroidX.concurrentFuturesKtx}"
}
