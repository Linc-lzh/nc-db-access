package nc.db.exception;

public class NetworkException extends BankDatabaseException {
    public NetworkException(String errorCode, String message) {
        super(errorCode, message);
    }

    public NetworkException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
