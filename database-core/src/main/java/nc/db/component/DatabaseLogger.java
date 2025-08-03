package nc.db.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

// DatabaseLogger.java
public class DatabaseLogger {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseLogger.class);

    public void logQuery(String sql, Object[] params, long executionTime) {
        if (logger.isDebugEnabled()) {
            logger.debug("Executed SQL: {} with params: {}, time: {}ms",
                    sql, Arrays.toString(params), executionTime);
        }

        // 慢查询日志
        if (executionTime > 1000) {
            logger.warn("Slow query detected ({}ms): {}", executionTime, sql);
        }
    }

    // 其他日志方法...
}

