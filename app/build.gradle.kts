plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    //la Docu está en C:\...\Frikartas\app\build\dokka\html
    id("org.jetbrains.dokka")
}

android {
    namespace = "com.example.frikartas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.frikartas"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes +="META-INF/DEPENDENCIES"
            excludes +="META-INF/LICENSE.md"
            excludes +="META-INF/LICENSE.txt"
            excludes +="META-INF/license.txt"
            excludes +="META-INF/NOTICE.md"
            excludes +="META-INF/NOTICE.txt"
            excludes +="META-INF/notice.txt"
            excludes +="META-INF/ASL2.0"
            excludes +="META-INF/*.kotlin_module"
            excludes +="META-INF/INDEX.LIST"
        }
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src\\main\\assets", "src\\main\\java\\assets")
            }
        }
    }


}

val hiltVersion = "2.48.1"

dependencies {

    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    // INTENTO de Hibernate, parece que no funciona bien en Android Studio
    //implementation("org.hibernate.orm:hibernate-core:6.4.2.Final")
    // Driver JDBC MySQL
    //implementation ("mysql:mysql-connector-java:8.0.33")
    // JNDI
    //implementation ("javax.naming:jndi:1.2.1")
    // Dependencia Java EE API que incluye JNDI
    //implementation("javax:javaee-api:8.0.1")
    // Jakarta EE API
    //implementation ("jakarta.platform:jakarta.jakartaee-api:8.0.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.0-rc01")
    implementation ("com.google.code.gson:gson:2.8.6")
    //implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}