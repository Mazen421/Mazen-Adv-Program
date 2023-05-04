import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;

    private String ID;
    private String email;
    private String username;
    private String password;

    private String address;
    private String phonenumber;



    public User(String name, String email, String username, String password, String address, String phonenumber, String ID) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phonenumber = phonenumber;
        this.ID = ID;
    }

    public List<Book> searchBooksById(List<Book> bookList, String bookId) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getISBN().equalsIgnoreCase(bookId) && book.isAvailable()) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchBooksByTitle(List<Book> bookList, String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchBooksByGenre(List<Book> bookList, String genre) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getGenre().equalsIgnoreCase(genre) && book.isAvailable()) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchBooksByAuthor(List<Book> bookList, String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equalsIgnoreCase(author) && book.isAvailable()) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    //TODO PUT INSIDE BOOKDATABASE INSTEAD OF USER




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String Add) {
        this.address = Add;
    }

    public void setPhonenumber(String pn) {
        this.phonenumber = pn;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public String getAddress() {
        return address;
    }
    public String getID() {
        return ID;
    }
    public void setID(String id) {
        this.ID = id;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", Phonenumber='" + phonenumber + '\'' +
                ", Address='" + address + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    public String toStringMini() {
        return "{ID='" + getID() + "', password='" + getPassword() + "'}";
    }
}