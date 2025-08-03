package nc.db.transaction;

/**
 * 事务状态描述对象
 */
public class TransactionStatus {
    private boolean rollbackOnly = false;
    private boolean completed = false;
    private Object transaction;
    // 标记事务为必须回滚
    public void setRollbackOnly() {
        if (this.completed) {
            throw new IllegalStateException("Transaction already completed");
        }
        this.rollbackOnly = true;
    }
    public boolean isRollbackOnly() {
        return this.rollbackOnly;
    }
    void markCompleted() {
        this.completed = true;
    }
    void setTransactionObject(Object transaction) {
        this.transaction = transaction;
    }
    @SuppressWarnings("unchecked")
    public <T> T getTransaction() {
        return (T) this.transaction;
    }
}
