package nc.db.transaction;

import nc.db.exception.TransactionException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// TransactionManager.java
public class TransactionManager {
    private final DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T execute(TransactionCallback<T> callback) throws SQLException, TransactionException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            T result = callback.doInTransaction(conn);

            conn.commit();
            return result;
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new TransactionException("Transaction failed", e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}

