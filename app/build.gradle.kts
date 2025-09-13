// App level
// all the Android-specific functionalities
plugins{
    alias(libs.plugins.android.application) // Build APKs
    alias(libs.plugins.kotlin.android) // Kotlin support
    alias(libs.plugins.kotlin.compose) // jetpack compose
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    alias(libs.plugins.google.gms.google.services)

}
// all the Android-specific build settings
android {
    namespace = "com.soumyadeep.pingme" // unique app id used only at build time
    compileSdk = 36 // Android SDK version used when compiling app’s code
// default build settings for app
    defaultConfig {
        applicationId = "com.soumyadeep.pingme" // unique ID for your APP on Android devices and Play Store
        minSdk = 29 // app will work on Android 10 and above
        targetSdk = 36 // app is tested and optimized for Android 15 behaviors
        versionCode = 1 // 1 means 1st release
        versionName = "1.0" // version string

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
// types of app built(release and debug)
    buildTypes {
        release {
            isMinifyEnabled = false // removing unused codes
            // used when minification is enabled.
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    // Java language version compatibility
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // The Java version your code is written in
        targetCompatibility = JavaVersion.VERSION_17 // The Java version compiled bytecode should run on (both must be same)
    }
    // Kotlin compiler settings
    kotlinOptions {
        jvmTarget = "17" // The Java bytecode version Kotlin should compile to (Must match compileOptions)
    }
    // Enables/disables Android build system features
    buildFeatures {
        compose = true // enables jetpack compose
    }
}
// external libraries
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    implementation(libs.firebase.database)
//    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //extra

    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
//    implementation("androidx.navigation:navigation-compose:2.7.2") // Jetpack Compose support for Navigation
    implementation("com.google.accompanist:accompanist-navigation-animation:0.34.0") // animated transitions between Compose navigation screens.
    implementation("androidx.compose.animation:animation:1.5.3") // Core Compose animation library
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")// Allows you to control system
    implementation("io.coil-kt:coil-compose:2.4.0")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.9.0-alpha04")

    // hilt DI
    implementation("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-android-compiler:2.56.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // Kotlinx Serialization (JSON support)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // Firebase BOM
    implementation(platform(libs.firebase.bom))

// Firebase modules
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.database.ktx)
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("androidx.compose.ui:ui-text")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("com.google.android.gms:play-services-auth:21.4.0")

}