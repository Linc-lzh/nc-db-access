package nc.db.entity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 支持的数据库类型枚举
 */
public enum DatabaseType {
    MYSQL("MySQL", "8.0"),
    POSTGRESQL("PostgreSQL", "13"),
    ORACLE("Oracle", "19c");
    private final String productName;
    private final String testedVersion;
    DatabaseType(String productName, String testedVersion) {
        this.productName = productName;
        this.testedVersion = testedVersion;
    }
    // 根据JDBC连接获取数据库类型
    public static DatabaseType fromConnection(Connection conn) throws SQLException {
        String dbName = conn.getMetaData().getDatabaseProductName();
        for (DatabaseType type : values()) {
            if (type.productName.equalsIgnoreCase(dbName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported database: " + dbName);
    }
}
