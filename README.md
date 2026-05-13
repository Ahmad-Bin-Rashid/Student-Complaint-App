# Student Complaint App

A simple Android application built as a class assignment for the course Mobile Application Development (MAD). It is designed for students to easily submit, view, and track their complaints. The app is built using Kotlin and integrates with Firebase Firestore for real-time cloud data storage.

## Features

- **Splash Screen:** A welcoming splash screen on app startup.
- **Complaint Submission:** Students can submit complaints including their Name, Roll Number, Title, Category, Priority, and Description.
- **Complaint Listing:** A central dashboard that displays all submitted complaints in a real-time feed using a `RecyclerView`.
- **Complaint Details:** A detailed view for each complaint showing the timestamp of submission, status (Pending/Resolved), and other submitted details.
- **Real-Time Updates:** Data is synchronized in real-time with Firebase Firestore.
- **Clean Architecture:** Utilizes a Repository pattern for abstracting Firestore logic.
- **Coroutines Support:** Smooth asynchronous operations without blocking the main UI thread.

## Tech Stack

- **Language:** Kotlin
- **UI Components:** XML Layouts, RecyclerView, FloatingActionButton, Spinners, Material Design Components
- **Architecture:** MVVM/Repository Pattern Concepts
- **Backend/Database:** Firebase Cloud Firestore
- **Asynchronous Programming:** Kotlin Coroutines & Lifecycle Scopes

## Project Structure

```
app/src/main/java/com/example/activity2/
│
├── adapter/
│   └── ComplaintAdapter.kt        # RecyclerView adapter for listing complaints
├── model/
│   └── Complaint.kt               # Data class representing a Complaint entity
├── repository/
│   └── ComplaintRepository.kt     # Handles Firestore interactions
│
├── AddComplaintActivity.kt        # Activity for creating a new complaint
├── ComplaintDetailActivity.kt     # Activity showing details of a specific complaint
├── ComplaintListActivity.kt       # Activity displaying the list of all complaints
├── MainActivity.kt                # Placeholder/Base main activity
└── SplashActivity.kt              # Entry point splash screen
```

## Setup and Installation

### Prerequisites

- Android Studio
- Android SDK (Target 36)
- Firebase Account

### Steps to Run

1. **Clone the repository** (if applicable) or open the project folder in Android Studio.
2. **Add Firebase Credentials:**
   - Go to the [Firebase Console](https://console.firebase.google.com/).
   - Create a new project (if you haven't already).
   - Add an Android app to the project with the package name `com.example.activity2`.
   - Download the `google-services.json` file.
   - Place the `google-services.json` file inside the `app/` directory of the project.
3. **Sync Gradle:** Click on "Sync Now" in Android Studio to download all required dependencies.
4. **Run the App:** Connect your Android device or start an emulator, and click the Run button (Shift + F10).

## Course Details

- **Subject:** Mobile Application Development (MAD)
- **Semester:** 6th
