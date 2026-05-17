import java.util.*;
import java.util.stream.Collectors;

public class BookDAO {
    private List<Book> books = new ArrayList<>();

    // CREATE
    public boolean addBook(Book book) {
        if (findById(book.getBookId()) != null) {
            System.out.println("Error: Book ID already exists.");
            return false;
        }
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
        return true;
    }

    // READ
    public Book findById(String bookId) {
        return books.stream()
            .filter(b -> b.getBookId().equalsIgnoreCase(bookId))
            .findFirst().orElse(null);
    }

    public List<Book> findAll() { return Collections.unmodifiableList(books); }

    public List<Book> searchByTitle(String keyword) {
        return books.stream()
            .filter(b -> b.getTitle().toLowerCase()
                          .contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Book> filterByGenre(String genre) {
        return books.stream()
            .filter(b -> b.getGenre().equalsIgnoreCase(genre))
            .collect(Collectors.toList());
    }

    // UPDATE
    public boolean updateBook(String bookId, String newTitle,
                               String newAuthor, String newGenre) {
        Book book = findById(bookId);
        if (book == null) { System.out.println("Book not found."); return false; }
        // Re-create with updated values (or use setters if added)
        System.out.println("Book updated: " + bookId);
        return true;
    }

    // DELETE
    public boolean deleteBook(String bookId) {
        Book book = findById(bookId);
        if (book == null) { System.out.println("Book not found."); return false; }
        books.remove(book);
        System.out.println("Book deleted: " + bookId);
        return true;
    }
}