# ktor-health

Health check for Ktor projects.

## Installation

Add dependency to your `build.gradle(.kts)` or `pom.xml`:

```kotlin
api("dev.kaccelero:ktor-health:0.1.0")
```

```xml

<dependency>
    <groupId>dev.kaccelero</groupId>
    <artifactId>ktor-health-jvm</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

Install the plugin, and optionally add some checks:

```kotlin
install(KtorHealth) {
    // Add a check on `/healthz` endpoint
    healthCheck("database") {
        // Check your database connection and return a boolean
    }
    // You can also create checks on `/readyz` with `readyCheck(...)`
    // Or a custom endpoint with `customCheck("/custom", "database") { ... }`
}
```
