import java.util.*;

public class TransactionDAO {
    private List<Transaction> transactions = new ArrayList<>();
    private int counter = 1;

    public void add(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public int nextId() {
        return counter++;
    }
}