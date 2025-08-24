package org.example

import java.util.Calendar
import java.util.Date

/**
 * A data class to represent a transaction.
 * @property id The unique ID of the transaction.
 * @property memberId The ID of the member involved.
 * @property itemId The ID of the item involved.
 * @property date The date of the transaction.
 * @property type The type of the transaction (borrow or return).
 */
data class Transaction(
    val id: String,
    val memberId: String,
    val itemId: String,
    val date: Date,
    val type: TransactionType
) {
    /**
     * Calculates the due date for a borrowed item.
     * @return A formatted due date string.
     */
    fun getDueDate(): String {
        // Assume a 14-day borrowing period
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.add(Calendar.DAY_OF_YEAR, 14)
        return LibraryUtils.formatDate(calendar.time)
    }
}

/**
 * A sealed class representing transaction types.
 * This restricts the type hierarchy to a fixed set of classes.
 */
sealed class TransactionType {
    object BORROW : TransactionType()
    object RETURN : TransactionType()
}
