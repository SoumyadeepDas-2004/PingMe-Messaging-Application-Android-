// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false // produces apk
    alias(libs.plugins.kotlin.android) apply false // kotlin support
    alias(libs.plugins.kotlin.compose) apply false // jetpack compose
    // apply false means "Make this plugin available to submodules, but do not apply it to the project itself"
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}