package org.example

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

/**
 * A utility object containing helpful functions for the library system.
 */
object LibraryUtils {
    /**
     * Generates a unique ID for a library item or member.
     * @param prefix The prefix for the ID (e.g., "Book", "Member").
     * @return A unique ID string.
     */
    fun generateItemId(prefix: String): String {
        return "${prefix.substring(0, 1).uppercase()}${Random.nextInt(1000, 9999)}"
    }

    /**
     * Formats a date object into a readable string.
     * @param date The Date object to format.
     * @return A formatted date string.
     */
    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }
}

/**
 * Calculates a compound late fee recursively.
 * @param baseFee The initial late fee.
 * @param daysLate The number of days late.
 * @return The calculated compound fee.
 */
fun calculateCompoundLateFee(baseFee: Double, daysLate: Int): Double {
    if (daysLate <= 0) {
        return baseFee
    }
    return calculateCompoundLateFee(baseFee * 1.05, daysLate - 1)
}
