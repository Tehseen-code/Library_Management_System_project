package org.example

/**
 * Abstract base class for all library items.
 * @property id The unique ID of the item.
 * @property title The title of the item.
 * @property isAvailable The availability status of the item.
 */
abstract class LibraryItem(val id: String, val title: String, var isAvailable: Boolean = true) {
    abstract fun getFormattedInfo(): String
    abstract fun displayInfo()
}

/**
 * Represents a book in the library.
 * @property author The author of the book.
 * @property isbn The ISBN of the book.
 * @property pages The number of pages in the book.
 */
class Book(id: String, title: String, val author: String, val isbn: String, val pages: Int) : LibraryItem(id, title) {
    override fun getFormattedInfo(): String = "Title: $title, Author: $author, Type: Book, Available: $isAvailable"
    override fun displayInfo() {
        println("Book: $title by $author ($pages pages)")
    }
}

/**
 * Represents a DVD in the library.
 * @property director The director of the DVD.
 * @property duration The duration in minutes.
 * @property genre The genre of the DVD.
 */
class DVD(id: String, title: String, val director: String, val duration: Int, val genre: String) : LibraryItem(id, title) {
    override fun getFormattedInfo(): String = "Title: $title, Director: $director, Type: DVD, Available: $isAvailable"
    override fun displayInfo() {
        println("DVD: $title directed by $director ($duration min)")
    }
}

/**
 * Represents a magazine in the library.
 * @property issueNumber The issue number of the magazine.
 * @property publisher The publisher of the magazine.
 */
class Magazine(id: String, title: String, val issueNumber: Int, val publisher: String) : LibraryItem(id, title) {
    override fun getFormattedInfo(): String = "Title: $title, Issue: $issueNumber, Type: Magazine, Available: $isAvailable"
    override fun displayInfo() {
        println("Magazine: $title, Issue #$issueNumber, Publisher: $publisher")
    }
}
