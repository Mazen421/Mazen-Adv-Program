package database;
public class DatabaseTypeMismatchException extends RuntimeException {

    public DatabaseTypeMismatchException(String message) {
        super(message);
    }
}