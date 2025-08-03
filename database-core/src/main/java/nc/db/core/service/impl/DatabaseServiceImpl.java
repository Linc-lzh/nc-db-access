package nc.db.core.service.impl;

import nc.db.core.service.DatabaseDao;

import java.util.List;

// DatabaseServiceImpl.java
public class DatabaseServiceImpl implements DatabaseService {
    private final DatabaseDao databaseDao;
    private final CacheManager cacheManager;
    private final TransactionManager transactionManager;

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
    public int[] batchUpdateWithTransaction(String sql, List<Object[]> paramsList) {
        return transactionManager.execute(() ->
                databaseDao.batchUpdate(sql, paramsList)
        );
    }

    // 其他服务方法...
}

