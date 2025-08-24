package org.example

import java.util.Date
import java.util.UUID

// Observer Pattern: An interface for classes that want to be notified of library events.
interface LibraryObserver {
    fun onLibraryEvent(message: String)
}

/**
 * Manages the library's inventory and member records.
 * Implements the Observer pattern to notify listeners of events.
 */
class Library {
    private val itemsById = mutableMapOf<String, LibraryItem>()
    private val membersById = mutableMapOf<String, Member>()
    private val borrowedItems = mutableMapOf<String, String>() // itemID -> memberID
    private val transactions = mutableListOf<Transaction>()
    private val observers = mutableSetOf<LibraryObserver>()

    // Performance Challenge: An index to quickly find books by author.
    // This provides O(1) average time complexity for lookups,
    // compared to a linear search which is O(n).
    private val authorIndex = mutableMapOf<String, MutableList<Book>>()

    /**
     * Adds an observer to the library.
     * @param observer The observer to add.
     */
    fun addObserver(observer: LibraryObserver) {
        observers.add(observer)
    }

    /**
     * Notifies all registered observers with a given message.
     * @param message The message to send to observers.
     */
    private fun notifyObservers(message: String) {
        observers.forEach { it.onLibraryEvent(message) }
    }

    /**
     * Adds a new item to the library's inventory.
     * @param item The item to add.
     */
    fun addItem(item: LibraryItem) {
        itemsById[item.id] = item
        // Update the author index if the added item is a Book
        if (item is Book) {
            val books = authorIndex.getOrPut(item.author) { mutableListOf() }
            books.add(item)
        }
    }

    /**
     * Registers a new member with the library.
     * @param member The member to register.
     */
    fun registerMember(member: Member) {
        membersById[member.getMemberId()] = member
    }

    /**
     * Allows a member to borrow an item.
     * @param memberId The ID of the member.
     * @param itemId The ID of the item.
     */
    fun borrowItem(memberId: String, itemId: String) {
        val member = membersById[memberId]
        val item = itemsById[itemId]
        if (member == null || item == null) {
            println("Error: Member or item not found.")
            notifyObservers("Failed borrow attempt: member $memberId, item $itemId not found.")
            return
        }
        if (item.isAvailable) {
            item.isAvailable = false
            borrowedItems[itemId] = memberId
            val transaction = Transaction(UUID.randomUUID().toString(), memberId, itemId, Date(), TransactionType.BORROW)
            transactions.add(transaction)
            println("${item.title} borrowed by ${member.getName()}. Due on ${transaction.getDueDate()}")
            notifyObservers("${item.title} borrowed by ${member.getName()}.")
        } else {
            println("Error: The item is currently unavailable.")
            notifyObservers("${item.title} is currently unavailable for borrow.")
        }
    }

    /**
     * Allows a member to return an item.
     * @param memberId The ID of the member.
     * @param itemId The ID of the item.
     */
    fun returnItem(memberId: String, itemId: String) {
        val item = itemsById[itemId]
        if (item == null || borrowedItems[itemId] != memberId) {
            println("Error: Cannot return item. Either item not found or not borrowed by this member.")
            notifyObservers("Failed return attempt: item $itemId not borrowed by member $memberId.")
            return
        }
        item.isAvailable = true
        borrowedItems.remove(itemId)
        val transaction = Transaction(UUID.randomUUID().toString(), memberId, itemId, Date(), TransactionType.RETURN)
        transactions.add(transaction)
        println("${item.title} returned successfully.")
        notifyObservers("${item.title} returned by member ${membersById[memberId]?.getName()}.")
    }

    /**
     * Gets an item by its ID.
     * @param id The ID of the item.
     * @return The LibraryItem or null if not found.
     */
    fun getItemById(id: String): LibraryItem? = itemsById[id]

    /**
     * Gets a member by their ID.
     * @param id The ID of the member.
     * @return The Member or null if not found.
     */
    fun getMemberById(id: String): Member? = membersById[id]

    /**
     * Finds books by a specific author using a less efficient linear search.
     * This is used for comparison with the optimized version.
     * @param author The author's name to search for.
     * @return A list of Books.
     */
    fun findBooksByAuthorLinear(author: String): List<Book> {
        return itemsById.values.filterIsInstance<Book>().filter { it.author.equals(author, ignoreCase = true) }
    }

    /**
     * Performance Challenge: Finds books by a specific author using a pre-computed index.
     * @param author The author's name to search for.
     * @return A list of Books.
     */
    fun findBooksByAuthorOptimized(author: String): List<Book> {
        return authorIndex[author]?.toList() ?: emptyList()
    }
}
