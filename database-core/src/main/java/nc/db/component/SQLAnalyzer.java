package nc.db.component;

import nc.db.exception.SQLSyntaxException;

// SQLAnalyzer.java
public interface SQLAnalyzer {
    void analyze(String sql) throws SQLSyntaxException;
}