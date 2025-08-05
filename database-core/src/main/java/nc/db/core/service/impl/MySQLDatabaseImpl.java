package nc.db.core.service.impl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

// MySQLDatabaseImpl.java - MySQL具体实现
public class MySQLDatabaseImpl extends AbstractDatabaseImpl {
    public MySQLDatabaseImpl(DataSource dataSource) {
        super.dataSource = dataSource;
        super.queryBuilder = new MySQLQueryBuilder();
//        super.sqlAnalyzer = new MySQLSQLAnalyzer();
    }

    @Override
    public <T> List<T> query(String sql, Map<String, Object> params, Class<T> clazz) {


        // MySQL特定的查询实现
        String result = queryBuilder.buildSelectQuery(clazz, params);
        return null;
    }

    @Override
    public <T> T queryOne(String sql, Object[] params, Class<T> clazz) {
        return null;
    }

    @Override
    public int[] batchUpdate(String sql, List<Object[]> paramsList) {
        return new int[0];
    }
}