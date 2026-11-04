# PhysicsMate

**A Java-based Android toolkit for physics and mathematics.**

PhysicsMate brings together physics calculators, symbolic and numerical mathematics tools, and a collection of built-in formula notes. It is a personal Android development project and is still under active development.

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Language-Java-orange?logo=openjdk&logoColor=white)
![Minimum SDK](https://img.shields.io/badge/Min%20SDK-26-blue)
![Status](https://img.shields.io/badge/Status-Work%20in%20Progress-orange)

---

## Contents

- [Features](#features)
  - [Physics](#physics)
  - [Mathematics](#mathematics)
  - [Formula Notes](#formula-notes)
  - [Interface](#interface)
- [Technology](#technology)
- [Build and Run](#build-and-run)
- [Project Structure](#project-structure)
- [Current Limitations](#current-limitations)
- [License](#license)

---

## Features

### Physics

#### Special Relativity

- **Length Contraction** — Calculate the proper length, contracted length, or relative speed when the other two quantities are known.
- **Time Dilation** — A navigation entry is present, but the calculator has not yet been implemented.

#### Gravitation

- **Newtonian Gravitational Force** — Calculate the gravitational force, either mass, or the separation using the other quantities.
- **Escape Velocity** — Calculate the escape velocity, the attracting body's mass, or its radius.

The following gravitation topics also appear in the navigation, but their calculators are not implemented yet:

`Gravitational Potential` · `Gravitational Potential Energy` · `Gravitational Acceleration` · `Orbital Velocity` · `Kepler's Law` · `Jeans Mass` · `Schwarzschild Radius`

The implemented gravitation calculators use SI-unit inputs and outputs.

### Mathematics

| Tool | What it does |
|---|---|
| **Variable Solver** | Numerically solves systems of equations in multiple variables. Equations can be added or removed dynamically. |
| **Expression Evaluator** | Evaluates a function at user-supplied variable values. For example, evaluate `sin(x)*y` with `x = pi, y = e`. |
| **Derivative Calculator** | Calculates higher-order derivatives, provides a simplified symbolic result, and includes graphing support. |
| **Integral Calculator** | Calculates higher-order and definite integrals, with symbolic simplification and graphing support. |
| **Orthonormalisation** | Orthonormalises a set of input vectors. |
| **Vector Addition** | Adds vectors represented in Cartesian components, polar coordinates, or direction-cosine form. |
| **Eigenvalue and Eigenvector Calculator** | Calculates eigenvalues and eigenvectors of square matrices with dimensions from 1 to 11. |

### Formula Notes

The **Notes** screen currently displays built-in example formulas grouped into:

- **Gravity** — Newton's law of gravitation and gravitational potential energy.
- **Electricity & Magnetism** — Coulomb's law and Gauss's law.

These examples are loaded into an in-memory model. A user-facing interface for adding or editing notes, and persistent storage across app launches, have not been implemented yet.

### Interface

- **Custom mathematical keyboard** for entering expressions.
- **Mathematical expression rendering** with LaTeX-style formatting.
- **Copy-to-clipboard support** for calculator results.
- **Graph plotting** in the calculus tools.

---

## Technology

### Project Configuration

| Component | Configuration |
|---|---|
| **Language** | Java |
| **Java source / target compatibility** | Java 11 |
| **IDE** | Android Studio |
| **Build scripts** | Gradle Kotlin DSL |
| **Android Gradle Plugin** | 8.12.0 |
| **Gradle wrapper** | 8.13 |
| **Compile SDK** | 35 |
| **Target SDK** | 35 |
| **Minimum SDK** | 26 (Android 8.0) |
| **Application ID** | `com.example.physicsmate` |

### Main Libraries

| Library | Purpose |
|---|---|
| [Symja / Matheclipse](https://github.com/axkr/symja_android_library) | Symbolic expressions, equation solving, calculus, and mathematical operations. |
| [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) | Plotting graphs. |
| [AndroidMath](https://github.com/gregcockroft/AndroidMath) | Rendering mathematical expressions. |
| [Material Components for Android](https://github.com/material-components/material-components-android) | Material UI components. |
| [AndroidX](https://developer.android.com/jetpack/androidx) | AppCompat, Navigation, Lifecycle, and related Android libraries. |
| [Paris](https://github.com/airbnb/paris) | Programmatic view styling. |

---

## Build and Run

### Requirements

- **Android Studio**
- **Android SDK Platform 35**
- **JDK 17 or newer** to run the Gradle / Android Gradle Plugin build
- An Android device or emulator running **Android 8.0 (API 26) or newer**

> **Note:** The app's Java source and target compatibility are set to Java 11. This is separate from the JDK version used to run Gradle.

### Open in Android Studio

1. Clone the repository:

   ```bash
   git clone https://github.com/NoRancidIntentions/PhysicsMate2.git
   cd PhysicsMate2
   ```

2. Open the project root in Android Studio.
3. Allow Gradle to sync and install any missing Android SDK components.
4. Select a device or emulator and run the `app` configuration.

### Build from the Command Line

Build the debug APK:

```bash
./gradlew :app:assembleDebug
```

Install the debug build on a connected device:

```bash
./gradlew :app:installDebug
```

On Windows, use `gradlew.bat` instead of `./gradlew`.

The generated debug APK is located at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

A release APK is also currently checked into the repository:

[`app/release/app-release.apk`](app/release/app-release.apk)

---

## Project Structure

The directory tree below shows the main source and configuration files. Resource folders and individual Java classes are grouped to keep the overview readable.

```text
PhysicsMate2/
+-- app/
|   +-- build.gradle.kts
|   +-- release/
|   |   `-- app-release.apk
|   `-- src/
|       `-- main/
|           +-- java/
|           |   `-- com/example/physicsmate/
|           |       +-- misc/
|           |       +-- model/
|           |       `-- ui/
|           `-- res/
+-- gradle/
|   `-- wrapper/
|       `-- gradle-wrapper.properties
+-- build.gradle.kts
+-- gradle.properties
+-- settings.gradle.kts
`-- README.md
```

### Source Packages

| Directory | Responsibility |
|---|---|
| `misc/` | Main activity and shared mathematical utility methods. |
| `model/` | Data models and adapters for home topics and formula notes. |
| `ui/` | Custom keyboard, navigation screens, physics and mathematics calculators, notes, and settings. |
| `res/` | Android layouts, navigation graph, themes, drawables, fonts, and other resources. |

---

## Current Limitations

PhysicsMate is still a work in progress. The main unfinished areas are:

- The **External Links** tab is currently a placeholder.
- The **Settings** screen is a scaffold; theme and font-size customization are not implemented.
- Formula notes are example data held in memory. There is no completed note editor or persistent notes database.
- Several physics topics are visible in the navigation but do not yet have calculator implementations, as listed in the [Physics](#physics) section.

---

## License

There is currently no license file in this repository. Until a license is added, the source code should be treated as **not licensed for reuse or redistribution**.
