package nc.db.database.sdk;

import nc.db.transaction.TransactionCallback;

import java.util.List;

// DatabaseSDK.java - 核心接口
public interface DatabaseSDK {
    // 查询接口
    <T> List<T> query(String sql, Object[] params, Class<T> clazz);
    <T> T queryOne(String sql, Object[] params, Class<T> clazz);

    // 更新接口
    int update(String sql, Object[] params);
    int[] batchUpdate(String sql, List<Object[]> paramsList);

    // 事务接口
    <T> T executeInTransaction(TransactionCallback<T> callback);

    // 特殊操作接口
    boolean acquireLock(String lockKey, long timeout);
    void releaseLock(String lockKey);

    // 其他扩展接口...
}
