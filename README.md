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




# Library Management System - Sample Output

This is a sample output of the console-based Library Management System, showcasing its core functionalities, including item management, member transactions, and a performance test.

---

### 1. Interactive Console Menu

When you run the `Main.kt` file, you will be presented with an interactive menu. You can enter a number to perform a specific action.

```

\--- Library Management System Menu ---

1.  Add a new library item
2.  Register a new member
3.  Borrow an item
4.  Return an item
5.  Search for items (Optimized Search)
6.  Performance Comparison
7.  Exit
    Please enter your choice:

<!-- end list -->

```

---

### 2. Adding and Registering Items

Here's an example of adding a new book and registering a new member. The system confirms each action with a success message.

```

Please enter your choice: 1
Add a new item:
Enter item type (Book, DVD, Magazine): Book
Enter title: The Kotlin Guide
Enter author: John Doe
Enter ISBN: 978-1234567890
Enter pages: 300
Item 'The Kotlin Guide' added successfully\!

Please enter your choice: 2
Register a new member:
Enter member name: Alice Johnson
Enter member email: alice@email.com
Member 'Alice Johnson' registered successfully\!

```

---

### 3. Borrowing and Returning Items

This section demonstrates a successful item borrow and return. You will also see a notification from the `NotificationService` (Observer Pattern) confirming the event.

```

Please enter your choice: 3
Borrow an item:
Enter member ID (e.g., M001): M001
Enter item ID (e.g., B001): B001
The Kotlin Guide borrowed by Alice Johnson. Due on 2025-09-08
[NOTIFICATION]: The Kotlin Guide borrowed by Alice Johnson.

Please enter your choice: 4
Return an item:
Enter member ID: M001
Enter item ID: B001
The Kotlin Guide returned successfully.
[NOTIFICATION]: The Kotlin Guide returned by member Alice Johnson.

```

---

### 4. Optimized Search Functionality

The program includes an optimized search function for books by author. Instead of a slow linear search, it uses a HashMap for quick lookups.

```

Please enter your choice: 5
Search for items:
Enter author name to search for: John Doe
Found Books:
Title: The Kotlin Guide, Author: John Doe, Type: Book, Available: true

```

---

### 5. Performance Comparison

This menu option demonstrates the performance benefits of using a hash map index (Optimized Search) over a simple linear search for a large dataset (100,000 items). The results show a significant difference in execution time.

```

Please enter your choice: 6
\--- Performance Test: Linear vs. Optimized Search ---
Linear Search Time (for 100000 books): 42ms
Optimized Search Time (for 100000 books): 0ms
The optimized search using a HashMap index is significantly faster.

```
*Note: The actual milliseconds may vary depending on your system, but the optimized search will always be orders of magnitude faster.*
```
