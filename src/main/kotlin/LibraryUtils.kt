package org.example

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Part 3.2: Extension Functions
fun List<LibraryItem>.filterByAvailability(available: Boolean): List<LibraryItem> =
    this.filter { it.isAvailable == available }

fun String.isValidEmail(): Boolean =
    this.matches(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}"))

fun LibraryItem.getFormattedInfo(): String {
    return "Title: $title, Type: ${getItemType()}, Available: $isAvailable"
}

// Part 4.1: Recursive Functions
fun calculateCompoundLateFee(baseFee: Double, days: Int): Double {
    if (days <= 0) return baseFee
    return calculateCompoundLateFee(baseFee * 1.05, days - 1)
}

fun <T : Comparable<T>> List<T>.recursiveBinarySearch(target: T, low: Int = 0, high: Int = size - 1): Int {
    if (low > high) return -1
    val mid = (low + high) / 2
    return when {
        this[mid] < target -> recursiveBinarySearch(target, mid + 1, high)
        this[mid] > target -> recursiveBinarySearch(target, low, mid - 1)
        else -> mid
    }
}

// Part 5.2: Companion Objects and Object Declarations
class LibraryUtils {
    companion object {
        const val MAX_BORROW_LIMIT = 5
        const val DEFAULT_BORROW_DAYS = 14
        private var idCounter = 0

        fun generateItemId(type: String): String {
            val prefix = type.first().uppercase()
            val number = String.format("%03d", ++idCounter)
            return "$prefix$number"
        }
    }
}

object LibraryConfig {
    val categories = listOf("Fiction", "Non-Fiction", "Reference", "Periodicals")
    val maxRenewalTimes = 2
    val lateFeeCap = 50.0
}