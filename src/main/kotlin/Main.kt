package org.example

import java.util.Scanner

fun main() {
    val library = Library()
    val scanner = Scanner(System.`in`)
    val members = mutableListOf<Member>()

    // Pre-populate with sample data to demonstrate functionality
    val book1 = Book(
        id = LibraryUtils.generateItemId("Book"),
        title = "The Kotlin Guide",
        author = "Tehssen ul hassan",
        isbn = "978-1234567890",
        pages = 300
    )
    val dvd1 = DVD(
        id = LibraryUtils.generateItemId("DVD"),
        title = "Kotlin Tutorial",
        director = "Jane Smith",
        duration = 120,
        genre = "Educational"
    )
    library.addItem(book1)
    library.addItem(dvd1)

    val member1 = Member("M001", "Alice Johnson", "alice@email.com")
    val member2 = Member("M002", "Bob Smith", "bob@email.com")
    members.add(member1)
    members.add(member2)
    library.registerMember(member1)
    library.registerMember(member2)

    // --- Interactive Console Menu ---
    var isRunning = true
    while (isRunning) {
        println("\n--- Library Management System Menu ---")
        println("1. Add a new library item")
        println("2. Register a new member")
        println("3. Borrow an item")
        println("4. Return an item")
        println("5. Search for items")
        println("6. Get library statistics")
        println("7. Exit")
        print("Please enter your choice: ")

        when (scanner.nextLine()) {
            "1" -> {
                println("Add a new item:")
                print("Enter item type (Book, DVD, Magazine): ")
                val type = scanner.nextLine().lowercase()
                print("Enter title: ")
                val title = scanner.nextLine()
                val newItem = when (type) {
                    "book" -> {
                        print("Enter author: ")
                        val author = scanner.nextLine()
                        print("Enter ISBN: ")
                        val isbn = scanner.nextLine()
                        print("Enter pages: ")
                        val pages = scanner.nextInt()
                        scanner.nextLine() // Consume newline
                        Book(LibraryUtils.generateItemId("Book"), title, author, isbn, pages)
                    }
                    "dvd" -> {
                        print("Enter director: ")
                        val director = scanner.nextLine()
                        print("Enter duration (minutes): ")
                        val duration = scanner.nextInt()
                        scanner.nextLine() // Consume newline
                        print("Enter genre: ")
                        val genre = scanner.nextLine()
                        DVD(LibraryUtils.generateItemId("DVD"), title, director, duration, genre)
                    }
                    "magazine" -> {
                        print("Enter issue number: ")
                        val issueNumber = scanner.nextInt()
                        scanner.nextLine() // Consume newline
                        print("Enter publisher: ")
                        val publisher = scanner.nextLine()
                        Magazine(LibraryUtils.generateItemId("Magazine"), title, issueNumber, publisher)
                    }
                    else -> {
                        println("Invalid item type.")
                        null
                    }
                }
                if (newItem != null) {
                    library.addItem(newItem)
                    println("Item '${newItem.title}' added successfully!")
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
                print("Enter search query (by author, title, etc.): ")
                val query = scanner.nextLine()
                val foundBooks = library.findBooksByAuthor(query)
                if (foundBooks.isNotEmpty()) {
                    println("Found Books:")
                    foundBooks.forEach { println(it.getFormattedInfo()) }
                } else {
                    println("No items found matching '$query'.")
                }
            }
            "6" -> {
                println("Library Statistics:")
                val stats = library.getLibraryStatistics()
                stats.forEach { (key, value) -> println("$key: $value") }
            }
            "7" -> {
                println("Exiting application. Goodbye!")
                isRunning = false
            }
            else -> println("Invalid choice. Please try again.")
        }
    }
}