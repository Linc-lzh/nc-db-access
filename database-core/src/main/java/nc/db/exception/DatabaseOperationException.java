package nc.db.exception;

import java.sql.SQLException;

public class DatabaseOperationException extends BankDatabaseException {

    public DatabaseOperationException(String errorCode, String message) {
        super(errorCode, message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause.getMessage());
    }


    public DatabaseOperationException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
