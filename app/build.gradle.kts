plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.examensegunda"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.examensegunda"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    val activity_version = ("1.1.0")
    val appcompat_version = ("1.6.1")
    val constraintlayout_version = ("2.1.4")
    val core_ktx_version = ("1.12.0")
    val coroutines_version = ("1.4.2")
    val kotlin_version = ("1.8.0")
    val lifecycle_version = ("2.6.2")
    val material_version = ("1.10.0")
    val nav_version = ("2.7.4")
    val room_version = ("2.5.2")


    implementation("androidx.appcompat:appcompat:$appcompat_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayout_version")
    implementation("androidx.core:core-ktx:$core_ktx_version")
    implementation("com.google.android.material:material:$material_version")

    // Lifecycle libraries
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Navigation libraries
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Room libraries

    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:$room_version")

    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    implementation ("de.hdodenhof:circleimageview:3.1.0")

    /*
    val room_version = "2.4.3"

    implementation (("androidx.room:room-runtime:$room_version"))
    kapt (("androidx.room:room-compiler:$room_version"))

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation (("androidx.room:room-ktx:$room_version"))

    // Lifecycle libraries
    implementation (("androidx.lifecycle:lifecycle-viewmodel-ktx:$2.3.1")
    implementation (("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")


    implementation (("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation (("androidx.navigation:navigation-ui-ktx:2.5.3")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation (("androidx.core:core-ktx:1.9.0")
    implementation (("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.7.0")
    implementation (("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation (("androidx.test.ext:junit:1.1.3")
    androidTestImplementation (("androidx.test.espresso:espresso-core:3.4.0")
*/
}
