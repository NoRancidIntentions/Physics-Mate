PhysicsMate
PhysicsMate is a native Android application for evaluating mathematical expressions and solving selected problems in physics. It is a personal learning project developed with Java and Android Studio.

The app brings together symbolic and numerical mathematics tools, physics calculators, and a small collection of built-in formula notes. It is a work in progress; unfinished screens are identified below rather than presented as completed features.
Features
Physics
Special relativity
- Length contraction: calculate the proper length, contracted length, or relative speed when the other two quantities are known.
- Time dilation: a navigation entry exists, but the calculator has not been implemented yet.
Gravitation
- Newtonian gravitational force: calculate the force, either mass, or the separation using the other quantities.
- Escape velocity: calculate escape velocity, the attracting body's mass, or its radius.
- Additional gravitation entries are present in the navigation, but are not yet implemented: gravitational potential, gravitational potential energy, gravitational acceleration, orbital velocity, Kepler's law, Jeans mass, and Schwarzschild radius.
The implemented gravitation calculators use SI-unit inputs and outputs.
Mathematics
- Variable solver: numerically solves systems of equations in multiple variables. Equations can be added or removed dynamically.
- Expression evaluator: evaluates a function at user-supplied variable values, for example sin(x)*y with assignments such as x = pi, y = e.
- Derivative calculator: calculates higher-order derivatives and provides a simplified symbolic result. It also includes graphing support.
- Integral calculator: calculates higher-order and definite integrals, with symbolic simplification and graphing support.
- Orthonormalisation: orthonormalises a set of input vectors.
- Vector addition: supports Cartesian components, polar coordinates, and direction-cosine input.
- Eigenvalue and eigenvector calculator: accepts square matrices with dimensions from 1 to 11.
Formula notes
The Notes screen currently displays built-in example formulas grouped under:
- Gravity
- Electricity & Magnetism
The current examples include Newton's law of gravitation, gravitational potential energy, Coulomb's law, and Gauss's law.

The notes are currently loaded into an in-memory model. A user-facing interface for adding or editing notes, and persistent storage across app launches, are not implemented yet.
Interface
- A custom in-app keyboard for entering mathematical expressions.
- Mathematical expression rendering with LaTeX-style formatting.
- Copy-to-clipboard support for calculator results.
- Graph plotting in the calculus tools.
Technology
- Language: Java (source and target compatibility set to Java 11)
- IDE / build system: Android Studio, Gradle Kotlin DSL
- Android Gradle Plugin: 8.12.0
- Gradle wrapper: 8.13
- Compile SDK: 35
- Target SDK: 35
- Minimum SDK: 26 (Android 8.0)
- Application ID: com.example.physicsmate
Main libraries
Library	Purpose
Symja / Matheclipse	Symbolic expressions, equation solving, calculus, and mathematical operations
MPAndroidChart	Plotting graphs
AndroidMath	Rendering mathematical expressions
Material Components for Android	Material UI components
AndroidX	AppCompat, Navigation, Lifecycle, and related Android libraries
Paris	Programmatic view styling


Build and run
Requirements
- Android Studio
- Android SDK Platform 35
- JDK 17 or newer to run the Gradle/Android Gradle Plugin build
- An Android device or emulator running Android 8.0 (API 26) or newer
The app's Java source compatibility is set to Java 11; this is separate from the JDK used to run Gradle.
Open in Android Studio
1. Clone the repository:git clone https://github.com/NoRancidIntentions/PhysicsMate2.git
   cd PhysicsMate2
2. Open the project root in Android Studio.
3. Allow Gradle to sync and install any missing Android SDK components.
4. Select a device or emulator and run the app configuration.
Build from the command line
Build the debug APK:
./gradlew :app:assembleDebug
Install the debug build on a connected device:
./gradlew :app:installDebug
On Windows, use gradlew.bat instead of ./gradlew.

The generated debug APK is located at:
app/build/outputs/apk/debug/app-debug.apk
A release APK is also currently checked into the repository at [app-release.apk](app/release/app-release.apk).
Project layout
app/
├── build.gradle.kts
├── release/
│   └── app-release.apk
└── src/main/
    ├── AndroidManifest.xml
    ├── java/com/example/physicsmate/
    │   ├── misc/       # Main activity and shared mathematical utilities
    │   ├── model/      # Home and formula-note data models
    │   └── ui/         # Keyboard, navigation, calculators, notes, and settings
    └── res/            # Layouts, navigation graph, themes, and other resources
Current limitations
This repository is still under development. In addition to the unfinished screens noted in the feature list:
- The External Links tab is currently a placeholder.
- The Settings screen is a scaffold; settings such as theme and font-size customization are not implemented.
- Formula notes are currently example data held in memory; there is no completed note editor or persistent notes database.
- Several physics topics are visible in the navigation but do not yet have calculator implementations.
License
There is currently no license file in this repository. Unless a license is added, the source code should be treated as not licensed for reuse or redistribution.
