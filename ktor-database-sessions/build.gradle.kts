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
            name.set("ktor-database-sessions")
            description.set("Sessions database extensions for Ktor.")
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
                api(project(":exposed-core"))
                api(libs.bundles.ktor.server.api)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.tests.mockk)
                implementation(libs.tests.h2)
            }
        }
    }
}
