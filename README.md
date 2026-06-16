# Pokédex Android

A simple Pokédex Android app built in Kotlin. It fetches Pokémon from the [Tyradex API](https://tyradex.app/) and displays them in a list, with a detail screen, search, type filter, and an animated splash screen.

## Features

- List of Pokémon loaded from the Tyradex API
- Detail screen with image, types, and stats
- Search by name
- Filter by type
- Animated splash screen

## Requirements

- Android Studio (latest stable version)
- Android SDK with minimum API 24 (Android 7.0)
- An Android emulator or a physical device
- An internet connection (the app calls a public API)

## How to run

1. Clone the project:
   ```bash
   git clone git@github.com:Bheutschi/KOT1-Pokedex.git
   ```
   (or unzip the project archive)
2. Open the project folder in Android Studio.
3. Wait for Gradle to sync (this downloads all dependencies automatically).
4. Select an emulator or connect a physical device.
5. Click **Run** (green arrow) or press **Shift + F10**.

## Tech stack

- Kotlin
- View Binding
- Retrofit + Gson (API calls)
- Coil (image loading)
- Coroutines (async)
- Material 3 (UI)

## Note

The app requires an internet connection to load Pokémon data. If there is no connection, an error message is shown.