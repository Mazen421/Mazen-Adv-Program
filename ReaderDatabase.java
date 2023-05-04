import java.util.ArrayList;
import java.util.List;

public class ReaderDatabase extends DummyDatabase<Reader> {

    public ReaderDatabase() {
        super();
    }

    @Override
    protected Reader createItem(String name, String email, String username, String password,String address, String phonenumber, String ID) {
        return new Reader(name, email, username, password, address, phonenumber, ID);
    }



    public List<Reader> searchUsers(String keyword) {
        List<Reader> foundReaders = new ArrayList<>();
        for (Reader reader : getItems()) {
            if (reader.getName().contains(keyword) || reader.getEmail().contains(keyword) || reader.getUsername().contains(keyword)) {
                foundReaders.add(reader);
            }
        }
        return foundReaders;
    }
}
