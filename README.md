# Xposed Connection Monitor

An **Xposed Framework module** for Android that monitors and logs all network URLs called by a target application. It intercepts outgoing network connections at two levels — standard Java `URLConnection` and **OkHttp** — and logs them to both the Xposed console and a local SQLite database.

## How It Works

The module hooks into:

1. **`java.net.URL` constructors** — captures URLs created via standard Java networking.
2. **OkHttp's `Exchange.writeRequestHeaders()`** — captures URLs from OkHttp-based HTTP requests.

All captured URLs are dispatched concurrently (via Kotlin coroutines) to multiple loggers using a strategy pattern:

- **ConsoleLogger** — outputs to the Xposed log console.
- **SqliteLogger** — persists URLs to a local SQLite database.

## Architecture

- **Koin** for dependency injection, keeping components loosely coupled and testable.
- **`XposedHelpersFacade`** wraps static Xposed helpers behind an interface for easy mocking in tests.
- **Strategy pattern** in `UrlLogger` dispatches to multiple logger implementations concurrently.
- **Unit tests** covering hooks and loggers using MockK + JUnit.

## Project Structure

```
app/src/main/java/com/github/fatihsokmen/connectionmonitor/
├── UrlConnectionMonitorModule.kt       # Entry point (IXposedHookLoadPackage)
├── di/                                 # Koin DI modules
├── hooks/
│   ├── UrlConnectionHook.kt            # java.net.URL interception
│   └── OkHttpConnectionHook.kt         # OkHttp interception
└── loggers/
    ├── ConsoleLogger.kt                # Xposed console output
    ├── SqliteLogger.kt                 # SQLite persistence
    └── UrlLogger.kt                    # Multi-logger orchestrator
```

## Tech Stack

- **Kotlin** (JVM target 1.8)
- **Xposed Framework** (API v82+)
- **OkHttp** (compile-only, for hooking)
- **Koin** (dependency injection)
- **Kotlin Coroutines** (async logging)
- **MockK + JUnit** (testing)
- **Android SDK 34** (minSdk 24)

## Setup

1. Install the [Xposed Framework](https://github.com/rovo89/Xposed) or a compatible alternative (e.g., LSPosed) on your device.
2. Build the module: `./gradlew assembleDebug`
3. Install the APK and enable the module in your Xposed manager.
4. Set the target package in `UrlConnectionMonitorModule.kt` (default: `com.datatheorem.xposedtest`).
5. Reboot and check the Xposed log for captured URLs.

## License

This project does not currently specify a license.
