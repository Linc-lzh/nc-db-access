package nc.db.Executor;

import nc.db.exception.NetworkException;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

// RetryExecutor.java
public class RetryExecutor {
    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final long DEFAULT_RETRY_DELAY_MS = 1000;

    public <T> T executeWithRetry(Callable<T> task) throws Exception {
        return executeWithRetry(task, DEFAULT_MAX_RETRIES, DEFAULT_RETRY_DELAY_MS);
    }

    public <T> T executeWithRetry(Callable<T> task, int maxRetries, long retryDelayMs) throws Exception {
        int retryCount = 0;
        Exception lastException = null;

        while (retryCount <= maxRetries) {
            try {
                return task.call();
            } catch (Exception e) {
                lastException = e;
                retryCount++;

                if (retryCount <= maxRetries && isRecoverableException(e)) {
                    Thread.sleep(retryDelayMs);
                    continue;
                }

                throw lastException;
            }
        }

        throw lastException;
    }

    private boolean isRecoverableException(Exception e) {
        return e instanceof SQLException
                || e instanceof TimeoutException
                || e instanceof NetworkException;
    }
}

