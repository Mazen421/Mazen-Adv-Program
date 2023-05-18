import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //MAIN IS FOR TESTS ONLY, THE MENU LOOP WILL BE ADDED LATER USING EVENTS FROM THE GUI.
    public static void main(String[] args) {
        LibSys.startRand(); // Populate the databases with random users, librarians, and books
        User loggedInUser;
        Book book = null; // Declare the book variable outside the if statement
        LibrarianDatabase Libbase = LibSys.getLibrarianDatabase();
        ReaderDatabase Readbase = LibSys.getReaderDatabase();


        while (true) {

            Librarian Admin = Libbase.getItems().get(0);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter anything about a User: ");
            String key = scanner.nextLine();

            List<Librarian> ListLib = new ArrayList<>();
            List<Reader> ListRead = new ArrayList<>();

            ListLib = Admin.SearchLibrarianByKeyword(key, Libbase);
            ListRead= Admin.SearchReaderByKeyword(key, Readbase);

            if(ListLib != null){

                for (int i = 0; i < ListLib.size(); i++) {
                    Librarian librarian = ListLib.get(i);
                    System.out.println((i + 1) + ". " + librarian.toString());
                }


            }

            if (ListRead != null){

                for (int i = 0; i < ListRead.size(); i++) {
                    Reader reader = ListRead.get(i);
                    System.out.println((i + 1) + ". " + reader.toString());
                }

            }
        }
    }
}
