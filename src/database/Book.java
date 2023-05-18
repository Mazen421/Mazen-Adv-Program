package database;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private int publicationYear;
    private boolean availability;

    private String heldBy; // New instance variable to track the reader holding the book

    private List<Reader> waitlist;




    public Book(String title, String author, String genre, String ISBN, int publicationYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.availability = true; // By default, the book is available
        this.heldBy = null;
        waitlist = new ArrayList<>();
    }


    // Getters and setters for the instance variables

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setHeldBy(String Readername) {
        this.heldBy = Readername;
    }

    public String getHeldBy() {
        return heldBy;
    }


    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    // Other methods specific to the Book class
    // ...
    public void addToWaitlist(Reader reader) {
        if (reader.getName() != getHeldBy()){
        waitlist.add(reader);}
        else {
            throw new DoubleBorrowException("Book already held by " + reader.getName());
        }
    }

    public void removeFromWaitlist(Reader reader) {
        waitlist.remove(reader);
    }

    public List<Reader> getWaitlist() {
        return waitlist;
    }

    public void displayWaitlist() {
        System.out.println("Waitlist for book '" + getTitle() + "':");
        for (int i = 0; i < waitlist.size(); i++) {
            Reader reader = waitlist.get(i);
            System.out.println((i + 1) + ". " + reader.getName());
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", publicationYear=" + publicationYear +
                ", availability=" + availability +
                '}';
    }
}