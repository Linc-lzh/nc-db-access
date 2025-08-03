package nc.db.database.sdk;

// DistributedLock.java
public class DistributedLock {
    private final DatabaseSDK databaseSDK;

    public boolean tryLock(String lockKey, long timeoutInSeconds) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeoutInSeconds * 1000) {
            if (acquireLockInDB(lockKey)) {
                return true;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        return false;
    }

    private boolean acquireLockInDB(String lockKey) {
        try {
            int affectedRows = databaseSDK.update(
                    "INSERT INTO distributed_locks (lock_key, lock_time) VALUES (?, ?) " +
                            "ON CONFLICT (lock_key) DO NOTHING",
                    new Object[]{lockKey, System.currentTimeMillis()});

            return affectedRows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void releaseLock(String lockKey) {
        databaseSDK.update("DELETE FROM distributed_locks WHERE lock_key = ?",
                new Object[]{lockKey});
    }
}

