# ktor-sentry

A Sentry plugin for Ktor

## Installation

Add dependency to your `build.gradle(.kts)` or `pom.xml`:

```kotlin
api("dev.kaccelero:ktor-sentry:0.1.0")
```

```xml

<dependencies>
    <dependency>
        <groupId>dev.kaccelero</groupId>
        <artifactId>ktor-sentry-jvm</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```

## Usage

```kotlin
fun Application.configureSentry() {
    install(KtorSentry) {
        dsn = "..."
        tracesSampleRate = 1.0 // 100% of traces, default is 0.0 (disabled)
    }
}
```

This plugin should be applied after `StatusPages` and `KtorHealth` (if used).

In case you are using Koin, you can register and use `ICaptureExceptionUseCase`:

```kotlin
single<ICaptureExceptionUseCase> { CaptureExceptionUseCase() }
```