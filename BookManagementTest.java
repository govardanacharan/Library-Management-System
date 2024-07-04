import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BookManagementTest {
    private BookManagement bookManagement;

    @BeforeEach
    public void setUp() {
        bookManagement = new BookManagement();
    }

    @Test
    public void testAddBook() {
        bookManagement.addBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true);
        Book book = bookManagement.getBookById("1");
        assertNotNull(book);
        assertEquals("The Great Gatsby", book.title);
        assertEquals("F. Scott Fitzgerald", book.author);
        assertEquals("Classic", book.genre);
        assertTrue(book.isAvailable);
    }

    @Test
    public void testDeleteBook() {
        bookManagement.addBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true);
        bookManagement.deleteBook("1");
        Book book = bookManagement.getBookById("1");
        assertNull(book);
    }

    @Test
    public void testUpdateBook() {
        bookManagement.addBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true);
        bookManagement.updateBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", false);
        Book book = bookManagement.getBookById("1");
        assertNotNull(book);
        assertFalse(book.isAvailable);
    }

    @Test
    public void testGetBookById() {
        bookManagement.addBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true);
        Book book = bookManagement.getBookById("1");
        assertNotNull(book);
        assertEquals("The Great Gatsby", book.title);
    }

    @Test
    public void testGetAllBooks() {
        bookManagement.addBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true);
        bookManagement.addBook("2", "To Kill a Mockingbird", "Harper Lee", "Classic", true);
        List<Book> books = bookManagement.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    public void testSearchBooksByTitle() {
        bookManagement.addBook("1", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true);
        bookManagement.addBook("2", "To Kill a Mockingbird", "Harper Lee", "Classic", true);
        List<Book> books = bookManagement.searchBooksByTitle("Gatsby");
        assertEquals(1, books.size());
        assertEquals("The Great Gatsby", books.get(0).title);
    }

    @Test
    public void testAddGenre() {
        bookManagement.addGenre("Classic");
        List<String> genres = bookManagement.getAllGenres();
        assertEquals(1, genres.size());
        assertEquals("Classic", genres.get(0));
    }

    @Test
    public void testUpdateGenre() {
        bookManagement.addGenre("Classic");
        bookManagement.updateGenre("Classic", "Modern Classic");
        List<String> genres = bookManagement.getAllGenres();
        assertEquals(1, genres.size());
        assertEquals("Modern Classic", genres.get(0));
    }

    @Test
    public void testGetAllGenres() {
        bookManagement.addGenre("Classic");
        bookManagement.addGenre("Science Fiction");
        List<String> genres = bookManagement.getAllGenres();
        assertEquals(2, genres.size());
        assertTrue(genres.contains("Classic"));
        assertTrue(genres.contains("Science Fiction"));
    }
}
