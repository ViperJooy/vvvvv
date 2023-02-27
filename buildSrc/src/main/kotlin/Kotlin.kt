/**
 * @author viper
 * @introduction 协程等kotlin内部使用类
 */
object Kotlin {
    private const val kotlin_version = Versions.Kotlin.kotlin_version

    //Kotlin 1.4 以后，您不再需要在 gradle 上声明 stdlib
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

    //协程
    object Coroutines {
        private const val version = Versions.Kotlin.coroutines_version
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }
}
