# 🪙 CoinWatcher

A modern, offline-first Android application that tracks cryptocurrency prices in real-time. Built with **Jetpack Compose** and the **MVVM Clean Architecture**.

## 🏗️ Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Architecture:** MVVM + Repository Pattern
* **Dependency Injection:** Dagger Hilt
* **Networking:** Retrofit + Moshi (JSON Parsing)
* **Local Database:** Room (SQLite)
* **Background Processing:** WorkManager
* **Image Loading:** Coil
* **Asynchronous:** Coroutines & Kotlin Flow

## 🚀 Key Features

* **Offline-First:** Uses a "Single Source of Truth" architecture. The UI observes the Database, not the API. Data remains available even without an internet connection.
* **Background Sync:** Utilizes `WorkManager` to fetch fresh data periodically in the background, ensuring the user always sees up-to-date prices upon opening the app.
* **Dynamic UI:** Real-time color coding (Green/Red) based on market trends.
* **Error Handling:** Robust error management using `try-catch` blocks in the Repository to prevent crashes during network failures.

## 🔧 Setup

1.  Clone the repository.
2.  Open in Android Studio.
3.  Sync Gradle.
4.  Run on Emulator or Physical Device.
    *(Note: No API Key required. Uses the public CoinPaprika API).*