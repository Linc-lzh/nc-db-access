package nc.db.transaction;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务回调接口（仿Spring设计）
 * @param <T> 返回结果类型
 */
@FunctionalInterface
public interface TransactionCallback<T> {
    /**
     * 在事务上下文中执行的代码
     * @param conn 数据库连接
     * @return 执行结果
     */
    T doInTransaction(Connection conn) throws Exception;
}
