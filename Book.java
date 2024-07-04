public class Book {
    String bookId;
    String title;
    String author;
    String genre;
    boolean isAvailable;

    public Book(String bookId, String title, String author, String genre, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
    }
}
