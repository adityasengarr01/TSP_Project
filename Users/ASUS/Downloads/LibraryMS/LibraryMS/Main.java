import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static LibraryService service = new LibraryService();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        service.addBook(new Book("BK001", "Intro to Algorithms", "Cormen", "Technology", 2009, 3));
        service.addMember(new Member("MEM001", "Priya Sharma", "priya@college.edu", "Computer Science", "2nd Year", "9876543210"));

        int choice;
        do {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book     2. View Books");
            System.out.println("3. Add Member   4. View Members");
            System.out.println("5. Issue Book   6. Return Book");
            System.out.println("7. Overdue List 0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> service.getAllBooks().forEach(System.out::println);
                case 3 -> addMember();
                case 4 -> service.getAllMembers().forEach(System.out::println);
                case 5 -> issueBook();
                case 6 -> returnBook();
                case 7 -> service.getOverdueRecords().forEach(System.out::println);
            }
        } while (choice != 0);
    }

    static void addBook() {
        System.out.print("ID: ");     String id     = sc.nextLine();
        System.out.print("Title: ");  String title  = sc.nextLine();
        System.out.print("Author: "); String author = sc.nextLine();
        System.out.print("Genre: ");  String genre  = sc.nextLine();
        System.out.print("Year: ");   int year      = sc.nextInt();
        System.out.print("Copies: "); int copies    = sc.nextInt(); sc.nextLine();
        service.addBook(new Book(id, title, author, genre, year, copies));
    }

    static void addMember() {
        System.out.print("ID: ");    String id    = sc.nextLine();
        System.out.print("Name: ");  String name  = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Dept: ");  String dept  = sc.nextLine();
        System.out.print("Year: ");  String yr    = sc.nextLine();
        System.out.print("Phone: "); String ph    = sc.nextLine();
        service.addMember(new Member(id, name, email, dept, yr, ph));
    }

    static void issueBook() {
        System.out.print("Member ID: "); String mid = sc.nextLine();
        System.out.print("Book ID: ");   String bid = sc.nextLine();
        System.out.println(service.issueBook(mid, bid, LocalDate.now().plusDays(14)));
    }

    static void returnBook() {
        System.out.print("Member ID: "); String mid = sc.nextLine();
        System.out.print("Book ID: ");   String bid = sc.nextLine();
        System.out.println(service.returnBook(mid, bid));
    }
}