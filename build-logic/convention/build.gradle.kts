plugins {
    `kotlin-dsl`
}

group = "io.github.fornewid.metro.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.pluginGradle)
    compileOnly(libs.kotlin.pluginGradle)
    compileOnly(libs.kotlin.composePluginGradle)
    compileOnly(libs.metro.pluginGradle)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "example.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "example.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "example.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidMetro") {
            id = "example.android.metro"
            implementationClass = "AndroidMetroConventionPlugin"
        }
    }
}
