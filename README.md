Sample Android Project

This project demonstrates various Android development concepts using **Jetpack Compose**, **Retrofit**, and **Clean Architecture**. It displays a list of articles fetched from a public API along with detailed information about each article.

---

## Features

- **Jetpack Compose**: Used for modern and declarative UI development.
- **Retrofit**: For seamless API integration.
- **Clean Architecture**: 
  - Separation of concerns across Presentation, Domain, and Data layers.
  - Enhanced reusability, testability, scalability, and maintainability.
- **Dependency Injection**: 
  - Efficient management of modules and initialization of dependencies.
- **Test Cases**:
  - Covers empty, data, and error scenarios for robust implementation.

---

## API Integration

This project uses the following public API for fetching articles:
- **API URL**: [https://newsapi.org/](https://newsapi.org/)

---

## Implementation Details

### 1. **Architecture Overview**
   - **Presentation Layer**: Handles UI and user interaction.
   - **Domain Layer**: Includes use cases and business logic.
   - **Data Layer**: Manages API calls and data repository.

### 2. **UI Implementation**
   - List of articles displayed using Jetpack Compose.
   - Detail screen to show complete information about a selected article.

### 3. **Error Handling**
   - Manages empty, error, and successful data states with appropriate UI feedback.

### 4. **Dependency Injection**
   - Implemented using modules to simplify dependency management and testing.



