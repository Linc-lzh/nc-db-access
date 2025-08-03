package nc.db.Executor;

import nc.db.exception.DatabaseOperationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

// BatchExecutor.java
public class BatchExecutor {
    private final DataSource dataSource;

    public BatchExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int[] executeBatch(String sql, List<Object[]> paramsList) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Object[] params : paramsList) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                stmt.addBatch();
            }

            return stmt.executeBatch();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Batch execution failed", e);
        }
    }

    // 其他批处理方法...
}
