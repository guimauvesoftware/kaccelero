plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.ksp)
    id("convention.publication")
}

repositories {
    maven("https://jitpack.io")
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("ktor-i18n-freemarker")
            description.set("An i18n plugin for Ktor Freemarker")
        }
    }
}

kotlin {
    jvmToolchain(21)
    jvm {
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":ktor-i18n"))
                api(libs.bundles.ktor.server.freemarker)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
