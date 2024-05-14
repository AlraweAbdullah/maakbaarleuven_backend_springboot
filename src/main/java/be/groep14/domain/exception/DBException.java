package be.groep14.domain.exception;

public class DBException extends RuntimeException{

    public DBException(String message) {
        super(message);
    }
}
