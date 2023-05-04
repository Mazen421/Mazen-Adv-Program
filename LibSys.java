import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class LibSys {
    private static ReaderDatabase readerDatabase;
    private static LibrarianDatabase librarianDatabase;
    private static BookDatabase bookDatabase;



    private static Login login;

    public static void start() {
        readerDatabase = new ReaderDatabase();
        librarianDatabase = new LibrarianDatabase();
        bookDatabase = new BookDatabase();


        login = new Login(readerDatabase, librarianDatabase);

        // Perform any necessary setup or configurations
        // ... TODO

        //Generate Admin Librarian
        Librarian admin = new Librarian("admin", "admin@example.com", "admin", "admin123", "Admin Address", "1234567890", "11111");
        librarianDatabase.addItem(admin);


        loginScreen();
    }

    public static void startRand() {
        readerDatabase = new ReaderDatabase();
        librarianDatabase = new LibrarianDatabase();
        bookDatabase = new BookDatabase();
        login = new Login(readerDatabase, librarianDatabase);

        // Perform any necessary setup or configurations
        // ...TODO
        populateRandomData();
        displayAll();
    }

    public static User loginScreen() {
        Scanner scanner = new Scanner(System.in);

        // Prompt for ID and password
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate credentials
        Reader reader = login.loginReader(id, password);
        Librarian librarian = login.loginLibrarian(id, password);

        // Provide appropriate access based on user type
        if (reader != null) {
            // Reader login successful
            // Perform reader-specific operations

            System.out.println("Reader login successful");
            return reader;
        } else if (librarian != null) {
            // Librarian login successful
            // Perform librarian-specific operations
            // ...
            System.out.println("Librarian login successful");
            return librarian;
        } else {
            // Invalid credentials
            System.out.println("Invalid ID or password. Please try again.");
            return null;
        }
    }

    private static void populateRandomData() {
        // Generate random readers and librarians
        readerDatabase.populateRand();
        librarianDatabase.populateRand();

        // Generate random books
        bookDatabase.populateRand();
    }

    public static void displayAll() {
        System.out.println("Readers:");
        List<Reader> readers = readerDatabase.getItems();
        for (int i = 0; i < readers.size(); i++) {
            Reader reader = readers.get(i);
            System.out.println((i + 1) + ". " + reader.toStringMini());
        }

        System.out.println("\nLibrarians:");
        List<Librarian> librarians = librarianDatabase.getItems();
        for (int i = 0; i < librarians.size(); i++) {
            Librarian librarian = librarians.get(i);
            System.out.println((i + 1) + ". " + librarian.toStringMini());
        }

        System.out.println("\nBooks:");
        List<Book> books = bookDatabase.getItems();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getTitle());
        }
    }
    public static ReaderDatabase getReaderDatabase() {
        return readerDatabase;
    }

    public static LibrarianDatabase getLibrarianDatabase() {
        return librarianDatabase;
    }

    public static BookDatabase getBookDatabase() {
        return bookDatabase;
    }

    public static void displayReaders() {
        System.out.println("Readers:");
        List<Reader> readers = readerDatabase.getItems();
        for (int i = 0; i < readers.size(); i++) {
            Reader reader = readers.get(i);
            System.out.println((i + 1) + ". " + reader.toStringMini());
        }
    }

    public static void displayLibrarians() {
        System.out.println("Librarians:");
        List<Librarian> librarians = librarianDatabase.getItems();
        for (int i = 0; i < librarians.size(); i++) {
            Librarian librarian = librarians.get(i);
            System.out.println((i + 1) + ". " + librarian.toStringMini());
        }
    }

    public static void displayAllBooks() {
        System.out.println("Books:");
        List<Book> books = bookDatabase.getItems();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getTitle());
        }
    }

    public static void displayAvailableBooks() {
        System.out.println("Available Books:");
        List<Book> books = bookDatabase.getItems();
        int count = 1;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(count++ + ". " + book.getTitle());
            }
        }
    }

    public static List<Book> getBorrowedBooks() {
        return bookDatabase.getBorrowedBooks();
    }



    public static void DisplayBorrowedBooksAndReader() {
        System.out.println( bookDatabase.borrowedBooksToString());
    }
    public static void DisplayBorrowedBooks(){
        List<Book> books = LibSys.getBorrowedBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.toString());
        }
    }



}
