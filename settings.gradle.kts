pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Plugins
            version("kotlin", "2.0.0")
            plugin("multiplatform", "org.jetbrains.kotlin.multiplatform").versionRef("kotlin")
            plugin("serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")
            plugin("kover", "org.jetbrains.kotlinx.kover").version("0.8.0")
            plugin("ksp", "com.google.devtools.ksp").version("2.0.0-1.0.21")

            // Kotlinx
            library("kotlinx-coroutines", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
            library("kotlinx-serialization-json", "org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0")
            library("kotlinx-datetime", "org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
            library("kotlin-js", "org.jetbrains.kotlin-wrappers:kotlin-js:1.0.0-pre.758")

            // Tests
            library("tests-mockk", "io.mockk:mockk:1.13.11")

            // Ktor
            version("ktor", "2.3.11")
            library("ktor-serialization-kotlinx-json", "io.ktor", "ktor-serialization-kotlinx-json").versionRef("ktor")
            library("ktor-server-core", "io.ktor", "ktor-server-core").versionRef("ktor")
            library("ktor-server-content-negotiation", "io.ktor", "ktor-server-content-negotiation").versionRef("ktor")
            library("ktor-server-sessions", "io.ktor", "ktor-server-sessions").versionRef("ktor")
            library("ktor-server-auth-jwt", "io.ktor", "ktor-server-auth-jwt").versionRef("ktor")
            library("ktor-server-freemarker", "io.ktor", "ktor-server-freemarker").versionRef("ktor")
            library("ktor-server-websockets", "io.ktor", "ktor-server-websockets").versionRef("ktor")
            library("ktor-client-core", "io.ktor", "ktor-client-core").versionRef("ktor")
            library("ktor-client-content-negotiation", "io.ktor", "ktor-client-content-negotiation").versionRef("ktor")
            library("ktor-client-auth", "io.ktor", "ktor-client-auth").versionRef("ktor")

            bundle(
                "ktor-server-api",
                listOf(
                    "ktor-server-core",
                    "ktor-server-content-negotiation",
                    "ktor-server-sessions",
                    "ktor-server-auth-jwt",
                    "ktor-serialization-kotlinx-json"
                )
            )
            bundle(
                "ktor-server-freemarker",
                listOf(
                    "ktor-server-core",
                    "ktor-server-freemarker"
                )
            )
            bundle(
                "ktor-server-websockets",
                listOf(
                    "ktor-server-core",
                    "ktor-server-websockets"
                )
            )
            bundle(
                "ktor-client-api",
                listOf(
                    "ktor-client-core",
                    "ktor-client-content-negotiation",
                    "ktor-serialization-kotlinx-json",
                    "ktor-client-auth"
                )
            )
        }
    }
}

rootProject.name = "kaccelero"
includeBuild("convention-plugins")
include(":core")

