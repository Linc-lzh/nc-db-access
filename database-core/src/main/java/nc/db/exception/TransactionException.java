package nc.db.exception;

public class TransactionException extends BankDatabaseException {

    public TransactionException(String errorCode, String message) {
        super(errorCode, message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
