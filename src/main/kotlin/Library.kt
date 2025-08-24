package org.example

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Part 2: Collections and HashMaps
class Library {
    private val itemsById = HashMap<String, LibraryItem>()
    private val itemsByCategory = HashMap<String, MutableList<LibraryItem>>()
    private val members = HashMap<String, Member>()
    private val borrowedItems = HashMap<String, MutableMap<String, LocalDate>>() // memberId to {itemId: dueDate}

    // Part 2.1: Library Inventory Management
    fun addItem(item: LibraryItem) {
        itemsById[item.id] = item
        itemsByCategory.getOrPut(item.getItemType()) { mutableListOf() }.add(item)
    }

    fun registerMember(member: Member) {
        members[member.getMemberId()] = member
    }

    fun borrowItem(memberId: String, itemId: String) {
        val member = members[memberId]
        val item = itemsById[itemId]

        if (member != null && item != null && item.isAvailable) {
            if (member.borrowItem(itemId)) {
                item.isAvailable = false
                val dueDate = LocalDate.now().plusDays(LibraryUtils.DEFAULT_BORROW_DAYS.toLong())
                borrowedItems.getOrPut(memberId) { mutableMapOf() }[itemId] = dueDate
                println("${item.title} borrowed by ${member.getName()}. Due on $dueDate")
            } else {
                println("Failed to borrow: Member has reached borrow limit.")
            }
        } else {
            println("Failed to borrow: Member or item not found, or item is not available.")
        }
    }

    fun returnItem(memberId: String, itemId: String) {
        val member = members[memberId]
        val item = itemsById[itemId]

        if (member != null && item != null && !item.isAvailable) {
            if (member.returnItem(itemId)) {
                item.isAvailable = true
                val borrowedItemInfo = borrowedItems[memberId]?.remove(itemId)
                val daysLate = if (borrowedItemInfo != null) {
                    val days = borrowedItemInfo.until(LocalDate.now()).days
                    if (days > 0) days else 0
                } else 0
                val lateFee = item.calculateLateFee(daysLate)

                println("${item.title} returned by ${member.getName()}. Days late: $daysLate. Late fee: $$lateFee")
            } else {
                println("Failed to return: Item was not borrowed by this member.")
            }
        } else {
            println("Failed to return: Member or item not found, or item is not borrowed.")
        }
    }

    // Part 3.1: Higher-Order Functions and Lambdas
    fun findBooksByAuthor(author: String): List<Book> {
        return itemsByCategory["Book"]
            ?.filterIsInstance<Book>()
            ?.filter { it.author == author }
            ?: emptyList()
    }

    fun <T : LibraryItem> findItemsBy(
        type: Class<T>,
        predicate: (T) -> Boolean
    ): List<T> {
        return itemsById.values
            .filterIsInstance(type)
            .filter(predicate)
    }

    fun getLibraryStatistics(): Map<String, Any> {
        val allItems = itemsById.values
        val totalItemsByType = allItems.groupBy { it.getItemType() }.mapValues { it.value.size }
        val availableCount = allItems.count { it.isAvailable }
        val totalCount = allItems.size
        val avgPages = allItems.filterIsInstance<Book>().map { it.pages }.average()
        val mostPopularGenre = allItems.filterIsInstance<DVD>()
            .groupBy { it.genre }
            .maxByOrNull { it.value.size }
            ?.key

        return mapOf(
            "totalItemsByType" to totalItemsByType,
            "averageBookPages" to if (avgPages.isNaN()) 0.0 else avgPages,
            "mostPopularDVDGenre" to (mostPopularGenre ?: "N/A"),
            "percentageAvailable" to if (totalCount > 0) (availableCount.toDouble() / totalCount) * 100 else 0.0
        )
    }

    fun processOverdueItems(action: (LibraryItem, Member, Int) -> Unit) {
        borrowedItems.forEach { (memberId, borrowedItemsMap) ->
            val member = members[memberId]
            borrowedItemsMap.forEach { (itemId, dueDate) ->
                val daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now()).toInt()
                val item = itemsById[itemId]
                if (daysLate > 0 && member != null && item != null) {
                    action(item, member, daysLate)
                }
            }
        }
    }
}