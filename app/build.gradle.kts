plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "github.alexzhirkevich.studentbsuby"
    compileSdk = 35

    defaultConfig {
        applicationId = namespace
        minSdk = 23
        targetSdk = 35
        versionCode = 24
        versionName = "1.0.3"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
//        debug {
//            minifyEnabled true
//            proguardFiles getDefaultProguardFile(")proguard-android-optimize.txt")), ")proguard-rules.pro")
//        }
        release {

            postprocessing {
                isRemoveUnusedCode = true
                isRemoveUnusedResources = true
                isOptimizeCode = true
                isObfuscate = false
            }
            proguardFiles(getDefaultProguardFile("proguard-android.txt"),"proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
//            minifyEnabled true
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    configurations {
        all {
            exclude(group= "xpp3", module= "xpp3")
        }
    }
    buildFeatures {
        compose =  true
        buildConfig = true
    }
    packagingOptions {
        resources {
            excludes += ("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
//    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation("com.google.firebase:firebase-analytics-ktx:22.0.2")
    implementation("com.google.firebase:firebase-crashlytics-ktx:19.0.3")
    implementation("com.google.firebase:firebase-config-ktx:22.0.0")
//    testimplementation("junit:junit:4.+")
//    androidTestimplementation("androidx.test.ext:junit:1.1.3")
//    androidTestimplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("androidx.compose.material:material-icons-extended")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.github.tomasharkema:RetrofitCurlPrinter:0.0.1")

    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.1")

    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.preference:preference-ktx:1.2.1")

    implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")

    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-compiler:2.52")

    ksp("androidx.hilt:hilt-compiler:1.2.0")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("org.jsoup:jsoup:1.14.3")

    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    implementation("me.onebone:toolbar-compose:2.3.0")

    implementation("de.charlex.compose:html-text:1.0.0")

    implementation("com.google.accompanist:accompanist-pager:0.22.0-rc")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.22.0-rc")
    implementation("com.google.accompanist:accompanist-insets-ui:0.22.0-rc")
    implementation("com.google.accompanist:accompanist-insets:0.22.0-rc")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.22.0-rc")
//
    implementation("com.github.skydoves:landscapist-glide:1.4.5")

    implementation("androidx.work:work-runtime-ktx:2.9.1")
    implementation("androidx.hilt:hilt-work:1.2.0")

    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    implementation("com.google.android.play:review:2.0.1")
    implementation("com.google.android.play:review-ktx:2.0.1")
    implementation("com.google.android.play:app-update:2.1.0")
    implementation("com.google.android.play:app-update-ktx:2.1.0")

    implementation("io.github.wanghonglin:crom-app-whitelist:0.1.2")
    implementation("com.github.mintrocket.MintPermissions:mintpermissions:1.1.1")

}