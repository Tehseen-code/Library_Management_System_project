import org.example.Book
import org.example.Library
import org.example.Member
import org.example.calculateCompoundLateFee
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class LibraryTest {

    private lateinit var library: Library
    private lateinit var book: Book
    private lateinit var member: Member

    @BeforeEach
    fun setUp() {
        // Reset the library and create test data before each test
        library = Library()
        book = Book("B001", "Test Book", "Test Author", "12345", 250)
        member = Member("M001", "Test Member", "test@email.com")
        library.addItem(book)
        library.registerMember(member)
    }

    @Test
    fun `test addItem adds item to library`() {
        assertNotNull(library.getItemById("B001"))
    }

    @Test
    fun `test registerMember adds member to library`() {
        assertNotNull(library.getMemberById("M001"))
    }

    @Test
    fun `test borrowItem successfully borrows item`() {
        library.borrowItem("M001", "B001")
        val borrowedBook = library.getItemById("B001")
        assertNotNull(borrowedBook)
        assertEquals(false, borrowedBook?.isAvailable)
    }

    @Test
    fun `test returnItem successfully returns item`() {
        library.borrowItem("M001", "B001")
        library.returnItem("M001", "B001")
        val returnedBook = library.getItemById("B001")
        assertNotNull(returnedBook)
        assertEquals(true, returnedBook?.isAvailable)
    }

    @Test
    fun `test findBooksByAuthorOptimized returns correct books`() {
        val book2 = Book("B002", "Another Test Book", "Test Author", "54321", 300)
        library.addItem(book2)
        val foundBooks = library.findBooksByAuthorOptimized("Test Author")
        assertEquals(2, foundBooks.size)
        assertTrue(foundBooks.any { it.id == "B001" })
        assertTrue(foundBooks.any { it.id == "B002" })
    }

    @Test
    fun `test findBooksByAuthorOptimized returns empty list for non-existent author`() {
        val foundBooks = library.findBooksByAuthorOptimized("Non Existent Author")
        assertTrue(foundBooks.isEmpty())
    }

    @Test
    fun `test getItemById returns null for non-existent item`() {
        assertNull(library.getItemById("B999"))
    }

    @Test
    fun `test calculateCompoundLateFee`() {
        // Test base case
        assertEquals(10.0, calculateCompoundLateFee(10.0, 0), 0.001)
        // Test for 1 day
        assertEquals(10.5, calculateCompoundLateFee(10.0, 1), 0.001)
        // Test for 2 days
        assertEquals(11.025, calculateCompoundLateFee(10.0, 2), 0.001)
    }
}