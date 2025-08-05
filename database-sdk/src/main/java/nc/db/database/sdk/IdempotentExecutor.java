//package nc.db.database.sdk;
//
//// IdempotentExecutor.java
//public class IdempotentExecutor {
//    private final DatabaseSDK databaseSDK;
//
//    public IdempotentExecutor(DatabaseSDK databaseSDK) {
//        this.databaseSDK = databaseSDK;
//    }
//
//    public <T> T execute(IdempotentOperation<T> operation) {
//        // 检查是否已经执行过
//        if (isOperationExecuted(operation.getIdempotencyKey())) {
//            return getPreviousResult(operation.getIdempotencyKey());
//        }
//
//        // 执行操作并记录结果
//        T result = operation.execute();
//        recordOperationResult(operation.getIdempotencyKey(), result);
//
//        return result;
//    }
//
//    private boolean isOperationExecuted(String idempotencyKey) {
//        return databaseSDK.queryOne(
//                "SELECT 1 FROM idempotency_records WHERE idempotency_key = ?",
//                new Object[]{idempotencyKey},
//                Integer.class) != null;
//    }
//
//    private <T> T getPreviousResult(String idempotencyKey) {
//        // 实现根据key查询之前的结果
//        // 根据实际情况可能需要进行序列化/反序列化
//        return null;
//    }
//
//    private <T> void recordOperationResult(String idempotencyKey, T result) {
//        // 实现结果记录逻辑
//    }
//}
//
