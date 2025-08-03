package nc.db.core.service;

import java.util.Map;

public interface QueryBuilder {
    String buildSelectQuery(Class<?> entityClass, Map<String, Object> conditions);
    String buildInsertQuery(Object entity);
    String buildUpdateQuery(Object entity, Map<String, Object> conditions);
    String buildDeleteQuery(Class<?> entityClass, Map<String, Object> conditions);
}
