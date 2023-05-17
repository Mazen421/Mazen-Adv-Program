package database;

public class Login {
    private ReaderDatabase readerDatabase;
    private LibrarianDatabase librarianDatabase;

    public Login(ReaderDatabase readerDatabase, LibrarianDatabase librarianDatabase) {
        this.readerDatabase = readerDatabase;
        this.librarianDatabase = librarianDatabase;
    }

    public Reader loginReader(String readerId, String password) {
        for (Reader reader : readerDatabase.getItems()) {
            if (reader.getID().equals(readerId) && reader.getPassword().equals(password)) {
                return reader;
            }
        }
        return null;
    }

    public Librarian loginLibrarian(String librarianId, String password) {
        for (Librarian librarian : librarianDatabase.getItems()) {
            if (librarian.getID().equals(librarianId) && librarian.getPassword().equals(password)) {
                return librarian;
            }
        }
        return null;
    }
}
