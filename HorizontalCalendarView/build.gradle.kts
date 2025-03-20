plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.pramod.horizontalcalendarview"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])

                // Maven Coordinates
                groupId = "com.pramod.horizontalcalendarview"  // Change this to match your desired namespace
                artifactId = "horizontalcalendarview"          // Library name
                version = "1.0.0"                              // Version number

                // Add POM metadata for better clarity
                pom {
                    name.set("Horizontal Calendar View")
                    description.set("A customizable horizontal calendar view for Android.")
                    url.set("https://github.com/pramodkhandare/horizontalcalendarview")  // Repository URL
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("pramodkhandare")
                            name.set("Pramodkumar Nathrav Khandare")
                            email.set("pramodkhandare@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:github.com/pramodkhandare/horizontalcalendarview.git")
                        developerConnection.set("scm:git:ssh://github.com/pramodkhandare/horizontalcalendarview.git")
                        url.set("https://github.com/pramodkhandare/horizontalcalendarview")
                    }
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
