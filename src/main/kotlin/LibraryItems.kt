package org.example

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Part 5.1: Sealed Class for Transaction Types
sealed class TransactionType {
    object Borrow : TransactionType()
    object Return : TransactionType()
    data class Renew(val newDueDate: LocalDate) : TransactionType()
}

// Part 1.1: Base Classes and Inheritance
abstract class LibraryItem(
    val id: String,
    val title: String,
    var isAvailable: Boolean = true
) {
    abstract fun getItemType(): String
    abstract fun calculateLateFee(daysLate: Int): Double
    open fun displayInfo(): String {
        return "ID: $id, Title: $title, Available: $isAvailable"
    }
}

class Book(
    id: String,
    title: String,
    val author: String,
    val isbn: String,
    val pages: Int
) : LibraryItem(id, title) {
    override fun getItemType(): String = "Book"
    override fun calculateLateFee(daysLate: Int): Double = daysLate * 0.50
    override fun displayInfo(): String {
        return "${super.displayInfo()}, Author: $author, Pages: $pages"
    }
}

class DVD(
    id: String,
    title: String,
    val director: String,
    val duration: Int, // in minutes
    val genre: String
) : LibraryItem(id, title) {
    override fun getItemType(): String = "DVD"
    override fun calculateLateFee(daysLate: Int): Double = daysLate * 1.00
    override fun displayInfo(): String {
        return "${super.displayInfo()}, Director: $director, Genre: $genre"
    }
}

class Magazine(
    id: String,
    title: String,
    val issueNumber: Int,
    val publisher: String
) : LibraryItem(id, title) {
    override fun getItemType(): String = "Magazine"
    override fun calculateLateFee(daysLate: Int): Double = daysLate * 0.25
    override fun displayInfo(): String {
        return "${super.displayInfo()}, Issue: $issueNumber, Publisher: $publisher"
    }
}