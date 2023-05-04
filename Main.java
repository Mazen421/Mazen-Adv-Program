import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibSys.startRand(); // Populate the databases with random users, librarians, and books

        // Perform any necessary setup or configurations
        // ...

        // Start the login screen
        User loggedInUser = LibSys.loginScreen();

        // Check if the logged-in user is a Reader
        /*
        if (loggedInUser instanceof Reader) {
            Reader reader = (Reader) loggedInUser;
            BookDatabase bookDatabase = LibSys.getBookDatabase();

            // Prompt the user to enter the book title
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the title of the book to borrow: ");
            String bookTitle = scanner.nextLine();

            // Search for the book in the database by title
            List<Book> matchingBooks = bookDatabase.searchBooks(bookTitle);

            if (!matchingBooks.isEmpty()) {
                Book book = matchingBooks.get(0); // Borrow the first matching book
                boolean success = reader.borrowBook(book, bookDatabase);

                if (success) {
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Unable to borrow the book.");
                }
            } else {
                System.out.println("No matching books found.");
            }
        }
        */

        LibSys.displayAvailableBooks();
        LibSys.DisplayBorrowedBooks();
        LibSys.DisplayBorrowedBooksAndReader();



    }
}