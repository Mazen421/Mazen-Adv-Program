import java.util.List;
import java.lang.System;

public class Librarian extends User {

    public Librarian(String name, String email, String username, String password, String address, String phoneNumber, String ID) {
        super(name, email, username, password, address, phoneNumber, ID);
    }

    public void editReaderInfo(Reader reader, String name, String email, String username, String password, String address, String phoneNumber, String ID) {
        reader.setName(name);
        reader.setEmail(email);
        reader.setUsername(username);
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

    public void addUser(Reader reader, ReaderDatabase userDatabase) {
        userDatabase.addItem(reader);
    }

    public void removeUser(Reader reader, ReaderDatabase userDatabase) {
        userDatabase.removeItem(reader);
    }

    public void addBook(Book book, BookDatabase bookDatabase) {
        bookDatabase.addItem(book);
    }

    //TODO THE ADDS SHOULD BE CALLING THE CONSTRUCTOR YOU RETARD

    public void removeBook(Book book, BookDatabase bookDatabase) {
        bookDatabase.removeItem(book);
    }

    public void displayAllReaders(ReaderDatabase ReaderDatabase) {
        System.out.println(ReaderDatabase.toString());
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", phoneNumber='" + getPhonenumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", ID='" + getID() + '\'' +
                '}';
    }
}
