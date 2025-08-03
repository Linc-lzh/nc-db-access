package nc.db.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行数据库操作框架的根异常
 * 所有自定义异常必须继承此异常
 */
public class BankDatabaseException extends RuntimeException {
    private final String errorCode;
    private final Map<String, Object> context = new HashMap<>();
    public BankDatabaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public BankDatabaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    // 添加上下文信息（用于异常诊断）
    public BankDatabaseException withContext(String key, Object value) {
        this.context.put(key, value);
        return this;
    }
    // 获取错误码（可用于国际化处理）
    public String getErrorCode() {
        return this.errorCode;
    }
    // 获取完整错误信息（包含上下文）
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder(super.getMessage());
        if (!context.isEmpty()) {
            sb.append(" [Context: ").append(context).append("]");
        }
        return sb.toString();
    }
}
