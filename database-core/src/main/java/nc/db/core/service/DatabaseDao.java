package nc.db.core.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

// DatabaseDao.java
public interface DatabaseDao {
    <T> List<T> query(String sql, Map<String, Object> params, Class<T> clazz);
    <T> T queryOne(String sql, Object[] params, Class<T> clazz);
    int update(String sql, Object[] params) throws SQLException;
    int[] batchUpdate(String sql, List<Object[]> paramsList);

    // 其他抽象方法...
}
