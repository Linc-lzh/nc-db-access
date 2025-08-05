package nc.db.exception;

import nc.db.entity.DatabaseType;

/**
 * SQL语法异常（受检异常）
 * 当SQL语句存在语法错误时抛出
 *
 * 包含以下特有属性：
 * - sqlSegment：出错的SQL片段
 * - position：错误位置（字符偏移量）
 * - databaseType：数据库类型
 */
public class SQLSyntaxException extends BankDatabaseException {
    private final String sqlSegment;
    private final Integer position;
    private final DatabaseType databaseType;
    // 标准错误码定义
    public static final String ERROR_CODE = "DB_400";


    public SQLSyntaxException(String message, String sqlSegment, Integer position, DatabaseType databaseType) {
        super(message, null);
        this.sqlSegment = sqlSegment;
        this.position = position;
        this.databaseType = databaseType;
    }
    public SQLSyntaxException(String sqlSegment, DatabaseType databaseType) {
        this(sqlSegment, null, databaseType, null);
    }
    public SQLSyntaxException(String sqlSegment, Integer position, DatabaseType databaseType) {
        this(sqlSegment, position, databaseType, null);
    }
    public SQLSyntaxException(String sqlSegment, Integer position,
                              DatabaseType databaseType, Throwable cause) {
        super(ERROR_CODE, buildMessage(sqlSegment, position, databaseType), cause);
        this.sqlSegment = sqlSegment;
        this.position = position;
        this.databaseType = databaseType;

        // 自动添加上下文
        withContext("SQL", sqlSegment)
                .withContext("Position", position)
                .withContext("DBType", databaseType);
    }

    private static String buildMessage(String sql, Integer pos, DatabaseType dbType) {
        StringBuilder sb = new StringBuilder("Invalid SQL syntax");
        if (dbType != null) {
            sb.append(" for ").append(dbType.name());
        }
        if (pos != null) {
            sb.append(" at position ").append(pos);
        }
        if (sql != null) {
            sb.append(", near: ").append(abbreviate(sql, 50));
        }
        return sb.toString();
    }
    private static String abbreviate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }
    // region Getter方法
    public String getSqlSegment() {
        return this.sqlSegment;
    }
    public Integer getPosition() {
        return this.position;
    }
    public DatabaseType getDatabaseType() {
        return this.databaseType;
    }
    // endregion
}
