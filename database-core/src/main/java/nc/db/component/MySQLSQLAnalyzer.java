package nc.db.component;

import nc.db.exception.SQLSyntaxException;

// MySQLSQLAnalyzer.java
public class MySQLSQLAnalyzer implements SQLAnalyzer {
    @Override
    public void analyze(String sql) throws SQLSyntaxException {
        // 分析SQL语法是否合法
        if (sql == null || sql.trim().isEmpty()) {
            throw new SQLSyntaxException("SQL cannot be empty", null, 0, null);
        }

        // 检查SQL注入风险
        if (containsSqlInjectionKeywords(sql)) {
            throw new SQLSyntaxException("Potential SQL injection detected", null, 0, null);
        }

        // 其他MySQL特定的语法检查...
    }

    private boolean containsSqlInjectionKeywords(String sql) {
        String[] dangerousKeywords = {"DROP", "TRUNCATE", "DELETE", "UPDATE", "UNION ALL", "OR 1=1"};

        for (String keyword : dangerousKeywords) {
            if (sql.toUpperCase().contains(keyword)) {
                return true;
            }
        }

        return false;
    }
}