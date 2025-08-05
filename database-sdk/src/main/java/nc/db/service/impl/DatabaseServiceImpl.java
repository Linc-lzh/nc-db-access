package nc.db.service.impl;

import nc.db.component.CacheManager;
import nc.db.core.service.DatabaseDao;
import nc.db.service.DatabaseService;
import nc.db.transaction.TransactionManager;

import java.sql.SQLException;
import java.util.List;

// DatabaseServiceImpl.java
public class DatabaseServiceImpl implements DatabaseService {
    private final DatabaseDao databaseDao;
    private final CacheManager cacheManager;
    private final TransactionManager transactionManager;

    public DatabaseServiceImpl(DatabaseDao databaseDao, CacheManager cacheManager, TransactionManager transactionManager) {
        this.databaseDao = databaseDao;
        this.cacheManager = cacheManager;
        this.transactionManager = transactionManager;
    }

    // 带缓存的查询服务
    @Override
    public <T> List<T> queryWithCache(String cacheKey, String sql, Object[] params, Class<T> clazz) {
        List<T> result = cacheManager.get(cacheKey);
        if (result == null) {
            result = databaseDao.query(sql, params, clazz);
            cacheManager.put(cacheKey, result);
        }
        return result;
    }

    // 带事务的批量更新
    @Override
    public int[] batchUpdateWithTransaction(String sql, List<Object[]> paramsList) throws SQLException {
        return transactionManager.execute((conn) ->
                databaseDao.batchUpdate(sql, paramsList)
        );
    }

    // 其他服务方法...
}
