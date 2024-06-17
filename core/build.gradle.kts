plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.ksp)
    id("convention.publication")
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("core")
            description.set("Core of kaccelero.")
        }
    }
}

kotlin {
    // Tiers are in accordance with <https://kotlinlang.org/docs/native-target-support.html>
    // Tier 1
    macosX64()
    macosArm64()
    iosSimulatorArm64()
    iosX64()

    // Tier 2
    linuxX64()
    linuxArm64()
    watchosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    tvosSimulatorArm64()
    tvosX64()
    tvosArm64()
    iosArm64()

    // Tier 3
    mingwX64()
    watchosDeviceArm64()

    // jvm & js
    jvmToolchain(21)
    jvm {
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    js {
        binaries.library()
        nodejs()
        browser()
        //generateTypeScriptDefinitions() // Not supported for now because of collections etc...
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines)
                api(libs.kotlinx.serialization.json)
                api(libs.kotlinx.datetime)
            }
        }
        val nativeMain by getting {}
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.tests.mockk)
            }
        }
        val desktopAndJsMain by creating {
            dependsOn(commonMain)
        }
        val jsMain by getting {
            dependsOn(desktopAndJsMain)
            dependencies {
                api(libs.kotlin.js)
            }
        }
        val desktopMain by creating {
            dependsOn(nativeMain)
            dependsOn(desktopAndJsMain)
        }
        val mingwMain by getting {
            dependsOn(desktopMain)
        }
        val linuxMain by getting {
            dependsOn(desktopMain)
        }
    }
}
