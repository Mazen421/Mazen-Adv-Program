import java.util.List;
import java.lang.System;
import java.util.Scanner;

public class Librarian extends User {



    public Librarian(String name, String email, String password, String address, String phoneNumber, String ID) {
        super(name, email, password, address, phoneNumber, ID);
    }

    public void editReaderInfo(Reader reader, String name, String email, String password, String address, String phoneNumber, String ID) {
        reader.setName(name);
        reader.setEmail(email);

        reader.setPassword(password);
        reader.setAddress(address);
        reader.setPhonenumber(phoneNumber);
        reader.setID(ID);
    }

    public List<Book> getBorrowedBooks(Reader reader) {
        return reader.getReaderBorrowedBooks();
    }

    public void banUser(Reader reader) {
        reader.setBanned(true);
    }

    public void unbanUser(Reader reader) {
        reader.setBanned(false);
    }

    public boolean addBorrowedBook(Reader reader, Book book) {
        if (book.isAvailable() && !reader.getReaderBorrowedBooks().contains(book)) {
            reader.getReaderBorrowedBooks().add(book);
            book.setAvailability(false);
            book.setHeldBy(reader.getName());
            return true;
        }
        return false;
    }

    public boolean removeBorrowedBook(Reader reader, Book book) {
        if (reader.getReaderBorrowedBooks().contains(book)) {
            reader.getReaderBorrowedBooks().remove(book);
            book.setAvailability(true);
            book.setHeldBy(null);
            return true;
        }
        return false;
    }

    public void addToDatabase(ReaderDatabase userDatabase) {
        String newname, newemail, newpassword,  newaddress, newphoneNumber, newID;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        newname = scanner.nextLine();

        System.out.println("Enter your Email: ");
        newemail = scanner.nextLine();


        System.out.println("Enter your Password: ");
        newpassword = scanner.nextLine();

        System.out.println("Enter your Address: ");
        newaddress = scanner.nextLine();

        System.out.println("Enter your Phone Number: ");
        newphoneNumber = scanner.nextLine();

        System.out.println("Enter your ID: ");
        newID = scanner.nextLine();

        Reader reader = new Reader( newname,  newemail,  newpassword,  newaddress,  newphoneNumber,  newID);

        userDatabase.addItem(reader);


        
    }

    public void removeFromDatabase(Reader reader, ReaderDatabase userDatabase) {
        userDatabase.removeItem(reader);
    }
    public void removeFromDatabase(Book book, BookDatabase bookDatabase) {
        bookDatabase.removeItem(book);
    }

    //OVERLOADING ACHIEVED BB!!!!111!1

    public void addToDatabase(BookDatabase bookDatabase) {
        String newtitle, newauthor, newgenre, newISBN;
        int newPublicationyaer;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your title: ");
        newtitle = scanner.nextLine();

        System.out.print("Enter your author: ");
        newauthor = scanner.nextLine();

        System.out.print("Enter your genre: ");
        newgenre = scanner.nextLine();

        System.out.print("Enter your ISBN: ");
        newISBN = scanner.nextLine();

        System.out.print("Enter your Publication Year: ");
        newPublicationyaer = scanner.nextInt();

        Book book = new Book(newtitle, newauthor, newgenre, newISBN, newPublicationyaer);
        bookDatabase.addItem(book);


    }




    public void displayAllReaders(ReaderDatabase ReaderDatabase) {
        System.out.println(ReaderDatabase.toString());
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phoneNumber='" + getPhonenumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", ID='" + getID() + '\'' +
                '}';
    }
}
