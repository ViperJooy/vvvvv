buildscript {
    repositories {
        google()
        mavenCentral()
//        jcenter() // Warning: this repository is going to shut down soon
        maven("https://jitpack.io") //maven { setUrl("https://jitpack.io") }
        gradlePluginPortal()
    }

    dependencies {
        classpath("${Gradle.plugin}")
        classpath("${Kotlin.plugin}")
        classpath("${AndroidX.Navigation.safeArgsGradlePlugin}")
        classpath("${AndroidX.Hilt.gradlePlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
//        jcenter() // Warning: this repository is going to shut down soon
        maven("https://jitpack.io")
        gradlePluginPortal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}