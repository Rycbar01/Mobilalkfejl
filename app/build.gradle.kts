plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.konyvwebshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.konyvwebshop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true;
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}



dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-storage:21.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-firestore:21.4.3")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.1.1")
    implementation("com.android.support:multidex:1.0.3")

    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation("androidx.cardview:cardview:1.0.0")
}