import java.util.ArrayList;
import java.util.List;

public class ReaderDatabase extends DummyDatabase<Reader> {

    public ReaderDatabase() {
        super();
    }

    @Override
    protected Reader createItem(String name, String email, String password,String address, String phonenumber, String ID) {
        return new Reader(name, email, password, address, phonenumber, ID);
    }

    public void addItem(Reader item) {
        if (item instanceof Reader) {
            super.addItem(item);
        } else {
            throw new DatabaseTypeMismatchException("Invalid item type. Only Reader objects are allowed.");
        }
    }



    public List<Reader> searchUsers(String keyword) {
        List<Reader> foundReaders = new ArrayList<>();
        for (Reader reader : getItems()) {
            if (reader.getName().contains(keyword) || reader.getEmail().contains(keyword)) {
                foundReaders.add(reader);
            }
        }
        return foundReaders;
    }
}
