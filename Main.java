import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibSys.startRand(); // Populate the databases with random users, librarians, and books
        User loggedInUser;
        Book book = null; // Declare the book variable outside the if statement
        loggedInUser = LibSys.loginScreen();


        while (true) {
            // Start the login screen




            // Check if the logged-in user is a Reader
            if (loggedInUser instanceof Librarian) {
                System.out.println("Librarian detected, executing order 66.\n");
                Librarian Librarian = (Librarian) loggedInUser;
                ReaderDatabase readerDatabase = LibSys.getReaderDatabase();
                BookDatabase bookDatabase = LibSys.getBookDatabase();

                // Prompt the user to enter the book title
                Librarian.addToDatabase(readerDatabase);
                Librarian.addToDatabase(bookDatabase);




            }
            else{

                System.exit(1);


            }

            LibSys.displayAll();

            
        }

        
    }
}
