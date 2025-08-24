package org.example

/**
 * Design Pattern: Factory Pattern.
 * This object is a factory for creating different types of LibraryItems.
 * It encapsulates the logic of object creation, making it easier to add new item types.
 */
object LibraryItemFactory {
    /**
     * Creates a new LibraryItem based on the provided type and properties.
     * @param type The type of item to create ("Book", "DVD", "Magazine").
     * @param args The properties for the new item.
     * @return A new LibraryItem object or null if the type is unknown.
     */
    fun createItem(type: String, vararg args: Any): LibraryItem? {
        return when (type.lowercase()) {
            "book" -> {
                val title = args[0] as String
                val author = args[1] as String
                val isbn = args[2] as String
                val pages = args[3] as Int
                Book(LibraryUtils.generateItemId("Book"), title, author, isbn, pages)
            }
            "dvd" -> {
                val title = args[0] as String
                val director = args[1] as String
                val duration = args[2] as Int
                val genre = args[3] as String
                DVD(LibraryUtils.generateItemId("DVD"), title, director, duration, genre)
            }
            "magazine" -> {
                val title = args[0] as String
                val issueNumber = args[1] as Int
                val publisher = args[2] as String
                Magazine(LibraryUtils.generateItemId("Magazine"), title, issueNumber, publisher)
            }
            else -> null
        }
    }
}
