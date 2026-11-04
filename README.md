# PhysicsMate — Legacy Implementation

**An earlier Java-based Android application for physics and mathematical problem solving.**

![Platform](https://img.shields.io/badge/platform-Android-3DDC84?logo=android&logoColor=white)
![Language](https://img.shields.io/badge/language-Java-orange?logo=openjdk&logoColor=white)
![Minimum SDK](https://img.shields.io/badge/minimum%20SDK-26-blue)
![Status](https://img.shields.io/badge/status-superseded-lightgrey)

> **This is the earlier version of PhysicsMate.** Development continued in the newer, refactored repository: **[NoRancidIntentions/PhysicsMate2](https://github.com/NoRancidIntentions/PhysicsMate2)**. Use PhysicsMate2 as the main project reference.

---

## About This Repository

This repository contains the original activity-oriented implementation of PhysicsMate. It was developed as a personal Android project to explore physics calculators, symbolic and numerical mathematics, custom input interfaces, and graphing.

The codebase is retained as an earlier stage of the project. Some screens are complete calculators, while others are experimental or unfinished. The feature summary below reflects the implementation found in this repository, not a claim that every menu item is production-ready.

## Features

### Physics

| Area | Functionality in this version |
|---|---|
| **Gravitation** | Calculators for Newtonian gravitational force, escape velocity, gravitational potential, gravitational potential energy, gravitational acceleration, and orbital velocity. |
| **Kinematics** | Solvers for the standard constant-acceleration equations, with graph support for selected motion calculations. |
| **Projectile Motion** | Projectile calculations, including a numerical implementation with an air-resistance parameter. |
| **Collisions** | Linear- and oblique-collision screens, including a numerical equation-solving approach to momentum-conservation problems. |
| **Special Relativity** | Length-contraction calculator. Additional screens for time dilation, velocity addition, Doppler effect, relativistic position, and relativistic time are present, but are incomplete or placeholders in this version. |

Additional gravitation screens are present for topics such as Kepler's third law, gravitational time dilation, and binary gravitational binding energy. Their implementation should be checked individually before relying on them.


https://github.com/user-attachments/assets/937beb20-d59e-4720-ac16-be9e7cbc7396

https://github.com/user-attachments/assets/308707c8-2167-4231-b232-b74ad3f65f1b

https://github.com/user-attachments/assets/9bcbfdf8-bc14-4670-8e51-e6b53c17b8f4

https://github.com/user-attachments/assets/e35a20c9-da45-4b24-b913-15b1b5c5002e

https://github.com/user-attachments/assets/ea10797c-cdb4-445d-a494-7ec44526fc76

https://github.com/user-attachments/assets/4e5da818-ea08-4b57-bc04-7c715d017f13

### Mathematics

- **Variable Solver** — numerically solve systems of equations with multiple unknowns.
- **Expression Evaluator** — evaluate mathematical expressions with assigned variable values.
- **Differentiation and Integration** — compute higher-order derivatives and integrals, simplify expressions symbolically, and evaluate definite integrals.
- **Graphing** — plot selected functions and calculus results.
- **Lagrange Interpolation** — construct an interpolation polynomial from input data points.
- **Orthonormalisation** — orthonormalise a set of vectors using the symbolic mathematics backend.
- **Vector Operations** — vector addition in Cartesian and polar forms, direction-cosine representation, and dot/cross products.
- **Triangle Solver** — solve triangle side/angle configurations using trigonometric relations.
- **Circle Graphing** — a circle-plotting workflow is present in the conic-section menu. Other conic-section entries are not fully wired into the interface.



https://github.com/user-attachments/assets/0bd8d263-fa9d-421f-bdff-5f234860d922



https://github.com/user-attachments/assets/4f4b14b1-4b73-4615-9cbd-ffcd06e904ec

There are many bugs in this app!


### External Tools

The External Links tab opens selected online tools, including:

- [GeoGebra Graphing Calculator](https://www.geogebra.org/graphing)
- [myPhysicsLab](https://www.myphysicslab.com/)
- [Derivative Calculator](https://www.derivative-calculator.net/)

These are external services; their functionality and availability are not provided by this app.

### Interface

- Custom mathematical keyboard.
- Mathematical notation rendering.
- Copy-to-clipboard support for calculator results.
- Graphing using MPAndroidChart.
- Tab-based navigation between Physics, Maths, and External Links.

---

## Technology Stack

### Project Configuration

| Component | Version / Setting |
|---|---|
| **Language** | Java |
| **Java source / target compatibility** | Java 8 |
| **Build scripts** | Gradle Groovy DSL |
| **Android Gradle Plugin** | 8.10.1 |
| **Gradle wrapper** | 8.11.1 |
| **Compile SDK** | 35 |
| **Target SDK** | 34 |
| **Minimum SDK** | 26 (Android 8.0) |
| **Application ID** | `com.example.physicsmate` |

### Main Dependencies

| Library | Purpose |
|---|---|
| [Symja / Matheclipse](https://github.com/axkr/symja_android_library) | Symbolic mathematics and expression evaluation. |
| [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) | Function and result plotting. |
| [AndroidMath](https://github.com/gregcockroft/AndroidMath) | Mathematical notation rendering. |
| [Material Components for Android](https://github.com/material-components/material-components-android) | UI components. |
| [AndroidX AppCompat](https://developer.android.com/jetpack/androidx) | Android compatibility support. |
| [Paris](https://github.com/airbnb/paris) | Programmatic view styling. |
| [Flexbox](https://github.com/google/flexbox-layout) | Flexible layouts. |

---

## Build and Run

### Requirements

- **Android Studio**
- **Android SDK Platform 35**
- **JDK 17 or newer** to run the Android Gradle Plugin build
- An Android device or emulator running **Android 8.0 (API 26) or newer**

### Clone and Open

```bash
git clone https://github.com/NoRancidIntentions/Physics-Mate.git
cd Physics-Mate
```

Open the project root in Android Studio, allow Gradle to sync, and select an Android device or emulator.

### Build from the Command Line

The Gradle wrapper is included in this repository.

Build the debug APK:

```bash
./gradlew :app:assembleDebug
```

Install it on a connected device:

```bash
./gradlew :app:installDebug
```

On Windows, use `gradlew.bat` instead of `./gradlew`.

The debug APK is generated at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

This repository also contains checked-in debug and release APKs under `app/debug/` and `app/release/`.

---

## Project Structure

This is a high-level view of the source tree. Generated build output and IDE-specific files are omitted.

```text
Physics-Mate/
+-- app/
|   +-- build.gradle
|   +-- src/
|   |   `-- main/
|   |       +-- java/com/example/physicsmate/
|   |       |   +-- Custom_methods.java
|   |       |   +-- activities/
|   |       |   |   +-- math/
|   |       |   |   `-- physics/
|   |       |   `-- ui/
|   |       |       +-- BaseActivity.java
|   |       |       `-- main/
|   |       `-- res/
|   +-- debug/
|   `-- release/
+-- gradle/
|   `-- wrapper/
+-- gradlew
+-- gradlew.bat
+-- build.gradle
+-- settings.gradle
`-- README.md
```

### Main Source Areas

| Directory / File | Responsibility |
|---|---|
| `activities/math/` | Mathematical calculators and graphing activities. |
| `activities/physics/` | Physics calculators, including gravitation, kinematics, collisions, and special relativity. |
| `ui/main/` | Main activity, tab navigation, and UI linking. |
| `ui/BaseActivity.java` | Shared activity base and keyboard integration. |
| `Custom_methods.java` | Shared mathematical and formatting utilities. |
| `res/` | Android layouts, drawables, themes, and other resources. |

---

## Development Status

This repository has been **superseded by [PhysicsMate2](https://github.com/NoRancidIntentions/PhysicsMate2)**. It is retained as a record of the earlier implementation rather than as the primary development target.

Some older screens are incomplete, and the code has not been presented as a validated scientific-computing package. For the current application structure and feature status, refer to the newer repository.

## License

This repository does not currently contain a license file. Until a license is added, the source code should be treated as **not licensed for reuse or redistribution**.
