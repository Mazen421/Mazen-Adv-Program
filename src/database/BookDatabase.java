package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookDatabase extends DummyDatabase<Book> {
    private List<Book> borrowedBooks;

    public BookDatabase() {
        super();
        borrowedBooks = new ArrayList<>();
    }

    @Override
    protected Book createItem(String name, String email, String password, String address, String phonenumber, String ID) {
        // Not applicable for BookDatabase
        return null;
    }

    protected Book createBook(String title, String author, String genre, String ISBN, int year) {
        return new Book(title, author, genre, ISBN, year);
    }

    @Override
    public void populateRand() {
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            String title = "Book " + (i + 1);
            String author = "Author " + (i + 1);
            String genre = "Genre " + (i + 1);
            String ISBN = generateRandomISBN();
            int year = 2000 + random.nextInt(22);

            Book book = createBook(title, author, genre, ISBN, year);
            addItem(book);
        }
    }

    private String generateRandomISBN() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 13; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }

    @Override
    public void addItem(Book item) {
        if (item instanceof Book) {
            super.addItem(item);
        } else {
            throw new DatabaseTypeMismatchException("Invalid item type. Only Book objects are allowed.");
        }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : getItems()) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword) || book.getGenre().contains(keyword)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public boolean borrowBook(Book book, Reader reader) {
        if (book.isAvailable() && reader.getBanned() == false) {
            book.setAvailability(false);
            book.setHeldBy(reader.getName());
            borrowedBooks.add(book);
            return true;
        }
        else {
            book.addToWaitlist(reader);
            return false;
            
            
        }
    }

    public boolean returnBook(Book book) {
        if (!book.isAvailable()) {
            book.setHeldBy(null);
            book.setAvailability(true);
            borrowedBooks.remove(book);
            return true;
        }
        else{
            return false;
        }
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String borrowedBooksToString() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Book book : borrowedBooks) {
            sb.append(count++).append(" - ").append(book.getTitle()).append(" HELD BY ").append(book.getHeldBy()).append("\n");
        }
        return sb.toString();
    }

}
