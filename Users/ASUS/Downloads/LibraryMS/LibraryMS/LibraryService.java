import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private BookDAO bookDAO           = new BookDAO();
    private MemberDAO memberDAO       = new MemberDAO();
    private TransactionDAO txnDAO     = new TransactionDAO();

    // ─── WRAPPER METHODS for Main.java ────────────────────────
    public void addBook(Book b)         { bookDAO.addBook(b); }
    public void addMember(Member m)     { memberDAO.addMember(m); }
    public List<Book> getAllBooks()      { return bookDAO.findAll(); }
    public List<Member> getAllMembers()  { return memberDAO.findAll(); }

    // ─── ISSUE BOOK ───────────────────────────────────────────
    public String issueBook(String memberId, String bookId, LocalDate dueDate) {
        Member member = memberDAO.findById(memberId);
        if (member == null)
            return "ERROR: Member ID '" + memberId + "' not found.";

        Book book = bookDAO.findById(bookId);
        if (book == null)
            return "ERROR: Book ID '" + bookId + "' not found.";

        if (!book.isAvailable())
            return "ERROR: No copies of '" + book.getTitle() + "' available.";

        boolean alreadyIssued = txnDAO.findAll().stream()
            .anyMatch(t -> t.getMemberId().equals(memberId)
                        && t.getBookId().equals(bookId)
                        && !t.isReturned());
        if (alreadyIssued)
            return "ERROR: This book is already issued to " + member.getName() + ".";

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        String txnId = "TXN" + String.format("%03d", txnDAO.nextId());
        txnDAO.add(new Transaction(txnId, memberId, bookId, dueDate));
        return "SUCCESS: '" + book.getTitle() + "' issued to " + member.getName()
             + ". Due: " + dueDate + " [" + txnId + "]";
    }

    // ─── RETURN BOOK ──────────────────────────────────────────
    public String returnBook(String memberId, String bookId) {
        Transaction txn = txnDAO.findAll().stream()
            .filter(t -> t.getMemberId().equals(memberId)
                      && t.getBookId().equals(bookId)
                      && !t.isReturned())
            .findFirst().orElse(null);

        if (txn == null)
            return "ERROR: No active issue record found for this member/book pair.";

        txn.setReturnDate(LocalDate.now());
        Book book = bookDAO.findById(bookId);
        if (book != null)
            book.setAvailableCopies(book.getAvailableCopies() + 1);

        String msg = "SUCCESS: Book returned.";
        if (LocalDate.now().isAfter(txn.getDueDate()))
            msg += " (Overdue by "
                 + (LocalDate.now().toEpochDay() - txn.getDueDate().toEpochDay())
                 + " days — fine applies)";
        return msg;
    }

    // ─── OVERDUE REPORT ───────────────────────────────────────
    public List<Transaction> getOverdueRecords() {
        return txnDAO.findAll().stream()
            .filter(Transaction::isOverdue)
            .collect(Collectors.toList());
    }
}