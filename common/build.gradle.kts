plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                // Used for async image loading
                api("io.github.qdsfdhvh:image-loader:1.2.3.1")

                // Needed only for preview.
                implementation(compose.preview)
            }
        }
        named("androidMain") {
            dependencies {
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
            }
        }

        named("desktopMain") {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
            }
        }
    }
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}