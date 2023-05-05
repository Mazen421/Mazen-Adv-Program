import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class DummyDatabase<T> {
    private List<T> items;

    public DummyDatabase() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    //TODO THROW THE EXCEPTION YOU CREATED RETARD

    public void removeItem(T item) {
        items.remove(item);
    }

    public List<T> getItems() {
        return items;
    }

    public List<T> getDatabase() {
        return new ArrayList<>(items);
    }


    public void populate(List<T> itemList) {
        items.addAll(itemList);
    }

    public void populateRand() {
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            String name = "User" + (i + 1);
            String email = name.toLowerCase() + "@example.com";
            String password = generateRandomPassword();
            String id = generateRandomID();
            String Null = "Null";

            T item = createItem(name, email, password, Null, Null, id);
            items.add(item);
        }
    }

    //TODO WHY TF DO YOU HAVE USERNAME??

    private String generateRandomID() {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    protected abstract T createItem(String name, String email, String password, String address, String phonenumber, String ID);

    private String generateRandomPassword() {
        Random random = new Random();
        int length = 8; // Password length

        StringBuilder password = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}