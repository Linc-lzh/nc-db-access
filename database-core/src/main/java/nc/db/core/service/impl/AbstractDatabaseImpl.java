package nc.db.core.service.impl;

import nc.db.component.SQLAnalyzer;
import nc.db.core.service.DatabaseDao;
import nc.db.core.service.QueryBuilder;
import nc.db.exception.DatabaseOperationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// AbstractDatabaseImpl.java - 抽象实现
public abstract class AbstractDatabaseImpl implements DatabaseDao {
    protected DataSource dataSource;
    protected QueryBuilder queryBuilder;
    protected SQLAnalyzer sqlAnalyzer;

    // 通用实现方法
    @Override
    public int update(String sql, Object[] params) throws SQLException {
        // 通用预检查
        sqlAnalyzer.analyze(sql);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Update operation failed", e);
        }
    }

    // 其他通用实现...
}





