package nc.db.service;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseService {
    <T> List<T> queryWithCache(String cacheKey, String sql, Object[] params, Class<T> clazz);
    int[] batchUpdateWithTransaction(String sql, List<Object[]> paramsList) throws SQLException;
}
