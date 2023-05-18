package database;

import java.util.ArrayList;
import java.util.List;

public class LibrarianDatabase extends DummyDatabase<Librarian> {

    public LibrarianDatabase() {
        super();
    }

    @Override
    protected Librarian createItem(String name, String email, String password, String address, String phonenumber, String ID) {
        return new Librarian(name, email, password, address, phonenumber, ID);
    }
    public void addItem(Librarian item) {
        if (item instanceof Librarian) {
            super.addItem(item);
        } else {
            throw new DatabaseTypeMismatchException("Invalid item type. Only Librarian objects are allowed.");
        }
    }




    public List<Librarian> searchLibrarians(String keyword) {
        List<Librarian> foundLibrarians = new ArrayList<>();
        for (Librarian librarian : getItems()) {
            if (librarian.getName().contains(keyword) || librarian.getEmail().contains(keyword) || librarian.getID().contains(keyword)) {
                foundLibrarians.add(librarian);
            }
        }

        if (foundLibrarians.isEmpty()){
            System.out.println("Librarian not found");
            return null;
        }
        else {
            return foundLibrarians;}
    }
}