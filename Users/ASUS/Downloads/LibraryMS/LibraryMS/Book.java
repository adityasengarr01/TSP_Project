public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private int year;
    private int totalCopies;
    private int availableCopies;

    public Book(String bookId, String title, String author,
                String genre, int year, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    // Getters & Setters
    public String getBookId()         { return bookId; }
    public String getTitle()          { return title; }
    public String getAuthor()         { return author; }
    public String getGenre()          { return genre; }
    public int    getYear()           { return year; }
    public int    getTotalCopies()    { return totalCopies; }
    public int    getAvailableCopies(){ return availableCopies; }
    public void   setAvailableCopies(int n) { this.availableCopies = n; }

    public boolean isAvailable() { return availableCopies > 0; }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s | Genre: %s | Available: %d/%d",
            bookId, title, author, genre, availableCopies, totalCopies);
    }
}