import java.util.ArrayList;
import java.util.List;

public class BookManagement {
    private List<Book> books = new ArrayList<>();
    private List<String> genres = new ArrayList<>();

    public void addBook(String bookId, String title, String author, String genre, boolean isAvailable) {
        books.add(new Book(bookId, title, author, genre, isAvailable));
        System.out.println("Book added successfully.");
    }

    public void deleteBook(String bookId) {
        books.removeIf(book -> book.bookId.equals(bookId));
        System.out.println("Book deleted successfully.");
    }

    public void updateBook(String bookId, String title, String author, String genre, boolean isAvailable) {
        for (Book book : books) {
            if (book.bookId.equals(bookId)) {
                book.title = title;
                book.author = author;
                book.genre = genre;
                book.isAvailable = isAvailable;
                System.out.println("Book updated successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public Book getBookById(String bookId) {
        for (Book book : books) {
            if (book.bookId.equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.title.toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public void addGenre(String genre) {
        if (!genres.contains(genre)) {
            genres.add(genre);
            System.out.println("Genre added successfully.");
        } else {
            System.out.println("Genre already exists.");
        }
    }

    public void updateGenre(String oldGenre, String newGenre) {
        int index = genres.indexOf(oldGenre);
        if (index != -1) {
            genres.set(index, newGenre);
            System.out.println("Genre updated successfully.");
        } else {
            System.out.println("Genre not found.");
        }
    }

    public List<String> getAllGenres() {
        return genres;
    }
}
