package org.example

import java.util.Scanner

/**
 * A concrete implementation of the LibraryObserver interface.
 * Logs events to the console.
 */
class NotificationService : LibraryObserver {
    override fun onLibraryEvent(message: String) {
        println("[NOTIFICATION]: $message")
    }
}

fun main() {
    val library = Library()
    val scanner = Scanner(System.`in`)
    val notificationService = NotificationService()
    library.addObserver(notificationService) // Observer Pattern

    // Pre-populate with sample data to demonstrate functionality
    val book1 = LibraryItemFactory.createItem("Book", "The Kotlin Guide", "John Doe", "978-1234567890", 300) as Book
    val dvd1 = LibraryItemFactory.createItem("DVD", "Kotlin Tutorial", "Jane Smith", 120, "Educational") as DVD
    library.addItem(book1)
    library.addItem(dvd1)

    val member1 = Member("M001", "Alice Johnson", "alice@email.com")
    library.registerMember(member1)

    // --- Interactive Console Menu ---
    var isRunning = true
    while (isRunning) {
        println("\n--- Library Management System Menu ---")
        println("1. Add a new library item")
        println("2. Register a new member")
        println("3. Borrow an item")
        println("4. Return an item")
        println("5. Search for items (Optimized Search)")
        println("6. Performance Comparison")
        println("7. Exit")
        print("Please enter your choice: ")

        when (scanner.nextLine()) {
            "1" -> {
                println("Add a new item:")
                print("Enter item type (Book, DVD, Magazine): ")
                val type = scanner.nextLine()
                print("Enter title: ")
                val title = scanner.nextLine()
                val newItem = when (type.lowercase()) {
                    "book" -> {
                        print("Enter author: ")
                        val author = scanner.nextLine()
                        print("Enter ISBN: ")
                        val isbn = scanner.nextLine()
                        print("Enter pages: ")
                        val pages = scanner.nextInt()
                        scanner.nextLine()
                        LibraryItemFactory.createItem(type, title, author, isbn, pages)
                    }
                    "dvd" -> {
                        print("Enter director: ")
                        val director = scanner.nextLine()
                        print("Enter duration (minutes): ")
                        val duration = scanner.nextInt()
                        scanner.nextLine()
                        print("Enter genre: ")
                        val genre = scanner.nextLine()
                        LibraryItemFactory.createItem(type, title, director, duration, genre)
                    }
                    "magazine" -> {
                        print("Enter issue number: ")
                        val issueNumber = scanner.nextInt()
                        scanner.nextLine()
                        print("Enter publisher: ")
                        val publisher = scanner.nextLine()
                        LibraryItemFactory.createItem(type, title, issueNumber, publisher)
                    }
                    else -> {
                        println("Invalid item type.")
                        null
                    }
                }
                newItem?.let {
                    library.addItem(it)
                    println("Item '${it.title}' added successfully!")
                }
            }
            "2" -> {
                println("Register a new member:")
                print("Enter member name: ")
                val name = scanner.nextLine()
                print("Enter member email: ")
                val email = scanner.nextLine()
                val newMember = Member(LibraryUtils.generateItemId("Member"), name, email)
                library.registerMember(newMember)
                println("Member '${newMember.getName()}' registered successfully!")
            }
            "3" -> {
                println("Borrow an item:")
                print("Enter member ID (e.g., M001): ")
                val memberId = scanner.nextLine()
                print("Enter item ID (e.g., B001): ")
                val itemId = scanner.nextLine()
                library.borrowItem(memberId, itemId)
            }
            "4" -> {
                println("Return an item:")
                print("Enter member ID: ")
                val memberId = scanner.nextLine()
                print("Enter item ID: ")
                val itemId = scanner.nextLine()
                library.returnItem(memberId, itemId)
            }
            "5" -> {
                println("Search for items:")
                print("Enter author name to search for: ")
                val query = scanner.nextLine()
                val foundBooks = library.findBooksByAuthorOptimized(query)
                if (foundBooks.isNotEmpty()) {
                    println("Found Books:")
                    foundBooks.forEach { println(it.getFormattedInfo()) }
                } else {
                    println("No items found by author '$query'.")
                }
            }
            "6" -> {
                println("--- Performance Test: Linear vs. Optimized Search ---")
                // Dummy data for performance test
                val dummyAuthor = "Test Author"
                val largeNumberOfBooks = 100000
                for (i in 1..largeNumberOfBooks) {
                    val book = LibraryItemFactory.createItem("Book", "Book $i", dummyAuthor, "123456789$i", 100)
                    if (book != null) {
                        library.addItem(book)
                    }
                }

                // Measure Linear Search
                val startTimeLinear = System.currentTimeMillis()
                library.findBooksByAuthorLinear(dummyAuthor)
                val endTimeLinear = System.currentTimeMillis()
                val timeLinear = endTimeLinear - startTimeLinear
                println("Linear Search Time (for $largeNumberOfBooks books): ${timeLinear}ms")

                // Measure Optimized Search
                val startTimeOptimized = System.currentTimeMillis()
                library.findBooksByAuthorOptimized(dummyAuthor)
                val endTimeOptimized = System.currentTimeMillis()
                val timeOptimized = endTimeOptimized - startTimeOptimized
                println("Optimized Search Time (for $largeNumberOfBooks books): ${timeOptimized}ms")

                println("The optimized search using a HashMap index is significantly faster.")
            }
            "7" -> {
                println("Exiting application. Goodbye!")
                isRunning = false
            }
            else -> println("Invalid choice. Please try again.")
        }
    }
}
