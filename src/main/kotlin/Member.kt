package org.example

// Part 1.2: Member Class with Encapsulation
class Member(
    private val memberId: String,
    private var name: String,
    private var email: String
) {
    private val borrowedItems = mutableListOf<String>()

    fun getMemberId(): String = memberId
    fun getName(): String = name
    fun getEmail(): String = email
    fun getBorrowedItems(): List<String> = borrowedItems.toList()

    fun borrowItem(itemId: String): Boolean {
        if (borrowedItems.size < LibraryUtils.MAX_BORROW_LIMIT && itemId !in borrowedItems) {
            borrowedItems.add(itemId)
            return true
        }
        return false
    }

    fun returnItem(itemId: String): Boolean = borrowedItems.remove(itemId)
}