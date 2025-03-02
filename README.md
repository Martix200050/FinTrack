# FinTrack

**FinTrack** is an expense tracking app that allows users to add, view, and sort expenses, analyze them through charts, and select the currency for displaying expenses.

## Key Features

- **Expense Management**: Add, delete, and view expenses with comments, dates, and categories.
- **Expense Sorting**: Sort expenses by:
  - Most expensive
  - Least expensive
  - Most recent
- **Visual Analytics**: A chart displays your spending and shows which category had the highest expenses. View expenses by week, month, or year.
- **Currency Selection**: In settings, users can select between USD or UAH as the currency for displaying expenses.
- **Localization**: The project is localized into two languages and automatically adapts to the system's language.

## Technologies Used

- **Kotlin**: The main programming language used for the app's development.
- **Jetpack Compose**: A modern toolkit for building UIs with a declarative approach.
- **Room**: Local database storage for managing expenses.
- **DataStore**: Used for storing user preferences like currency selection.
- **StateFlow**: Used for state management, especially in the MVVM architecture.
- **Navigation**: The new Jetpack Compose navigation uses serialized objects to pass data between screens without routes.
- **Coroutines**: Utilized for asynchronous operations with Room database in the ViewModel for background tasks.
- **Material Design**: Leveraged Material Design components to create a modern and user-friendly interface.
- **MVVM**: The MVVM architecture is used for a clean separation of logic and UI.
