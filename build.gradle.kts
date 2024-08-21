// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.google.devtools.ksp") version "2.0.0-1.0.24" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false

}

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    val kotlin_version = "2.0.0"

    dependencies {

        classpath("com.android.tools.build:gradle:8.1.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.52")
        classpath("com.google.firebase:firebase-crashlytics-gradle:3.0.2")
    }
}

//taskclean(type: Delete) {
//    delete rootProject.buildDir
//}