import java.time.LocalDate;

public class Transaction {
    private String transactionId;
    private String memberId;
    private String bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate; // null if not returned

    public Transaction(String transactionId, String memberId,
                       String bookId, LocalDate dueDate) {
        this.transactionId = transactionId;
        this.memberId      = memberId;
        this.bookId        = bookId;
        this.issueDate     = LocalDate.now();
        this.dueDate       = dueDate;
        this.returnDate    = null;
    }

    public String    getTransactionId() { return transactionId; }
    public String    getMemberId()      { return memberId; }
    public String    getBookId()        { return bookId; }
    public LocalDate getIssueDate()     { return issueDate; }
    public LocalDate getDueDate()       { return dueDate; }
    public LocalDate getReturnDate()    { return returnDate; }
    public void      setReturnDate(LocalDate d) { this.returnDate = d; }

    public boolean isReturned() { return returnDate != null; }
    public boolean isOverdue()  {
        return !isReturned() && LocalDate.now().isAfter(dueDate);
    }

    public String getStatus() {
        if (isReturned()) return "Returned";
        if (isOverdue())  return "Overdue";
        return "Issued";
    }

    @Override
    public String toString() {
        return String.format("[%s] Member:%s Book:%s | Issued:%s Due:%s | %s",
            transactionId, memberId, bookId, issueDate, dueDate, getStatus());
    }
}