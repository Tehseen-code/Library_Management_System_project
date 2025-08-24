# Library Management System

### Project Overview
This is a console-based Library Management System built with Kotlin. 
This project serves as an assignment to demonstrate a strong understanding of core Kotlin concepts,
including Object-Oriented Programming (OOP), functional programming paradigms, collection management with HashMaps, and recursive algorithms.

### How to Run the Program

1.  **Clone the Repository**: Clone this project from your GitHub account to your local machine.
2.  **Open in IntelliJ IDEA**: Open the project folder in IntelliJ IDEA. The IDE will automatically recognize it as a Gradle project and sync the dependencies.
3.  **Run from `Main.kt`**: Navigate to the `Main.kt` file. Find the `main()` function and click the green "run" button next to its declaration to start the application. The program will launch and display an interactive menu in the console.


### Explanation of Design Decisions

* **OOP**: The project uses an abstract base class `LibraryItem` to create a common blueprint for all library items, ensuring a consistent interface. Concrete classes like `Book`, `DVD`, and `Magazine` inherit from it, demonstrating polymorphism. Encapsulation is used in the `Member` class to protect data by keeping properties private and providing public methods to access them.
* **Collections**: `HashMaps` are used in the `Library` class for efficient data management. Storing items by ID (`itemsById`) allows for quick lookup, while grouping items by category (`itemsByCategory`) facilitates filtered searches.
* **Functional Programming**: The code uses higher-order functions like `filter`, `map`, and `forEach` to write concise and expressive code for data manipulation and queries. For instance, `findBooksByAuthor` uses a functional approach to filter and process data.
* **Recursion**: The `calculateCompoundLateFee` function is implemented recursively to show how a problem can be solved by breaking it down into smaller, similar sub-problems.
* **Advanced Kotlin**: Modern Kotlin features like `data classes` for data-heavy objects (`Transaction`), a `sealed class` for a restricted type hierarchy (`TransactionType`), and `companion objects` for utility functions (`LibraryUtils`) are used to create a more robust and organized codebase.



### Examples of Implemented Concepts

#### **1. OOP and Inheritance**
```kotlin
// A new Book is created
val book1 = Book(
    id = LibraryUtils.generateItemId("Book"),
    title = "The Kotlin Guide",
    author = "John Doe",
    isbn = "978-1234567890",
    pages = 300
)

// The displayInfo() method from the base class is called
println(book1.displayInfo())
