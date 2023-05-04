import java.util.ArrayList;
import java.util.List;

public class Reader extends User {
    private List<Book> readerBorrowedBooks;
    private boolean isBanned;

    public Reader(String name, String email, String username, String password, String address, String phoneNumber, String ID) {
        super(name, email, username, password, address, phoneNumber, ID);
        this.readerBorrowedBooks = new ArrayList<>();
        this.isBanned = false;
    }

    public boolean borrowBook(Book book, BookDatabase bookDatabase) {
        if (isBanned) {
            return false; // Unable to borrow book due to being banned
        }

        boolean success = bookDatabase.borrowBook(book, this);

        if (success) {
            readerBorrowedBooks.add(book); // Track the borrowed book
            return true; // Book successfully borrowed
        } else {
            return false; // Book not available, added to the waitlist
        }
    }

    public boolean returnBook(Book book, BookDatabase bookDatabase) {
        if (bookDatabase.returnBook(book)) {
            book.setHeldBy(null); // Reset the 'heldBy' value to null
            readerBorrowedBooks.remove(book); // Remove the returned book from the tracking
            return true; // Book successfully returned
        }
        else {
            return false; // Unable to return book
        }
    }

    public void setBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    public boolean getBanned() {
        return isBanned;
    }

    public List<Book> getReaderBorrowedBooks() {
        return readerBorrowedBooks;
    }

    public String readerBorrowedBooksToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Borrowed Books: [");
        for (Book book : readerBorrowedBooks) {
            sb.append(book.getTitle()).append(", ");
        }
        if (!readerBorrowedBooks.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length()); // Remove the trailing comma and space
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Reader{" +
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
