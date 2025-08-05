package nc.db.core.service.impl;

import nc.db.core.service.QueryBuilder;

import java.util.Iterator;
import java.util.Map;

// MySQLQueryBuilder.java
public class MySQLQueryBuilder extends DynamicSQLBuilder implements QueryBuilder  {
    @Override
    public String buildSelectQuery(Class<?> entityClass, Map<String, Object> conditions) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(getTableName(entityClass));

        if (!conditions.isEmpty()) {
            query.append(" WHERE ");
            Iterator<Map.Entry<String, Object>> it = conditions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                query.append(entry.getKey()).append(" = ?");
                if (it.hasNext()) {
                    query.append(" AND ");
                }
            }
        }

        return query.toString();
    }


    @Override
    public String buildInsertQuery(Object entity) {
        return "";
    }

    @Override
    public String buildUpdateQuery(Object entity, Map<String, Object> conditions) {
        return "";
    }

    @Override
    public String buildDeleteQuery(Class<?> entityClass, Map<String, Object> conditions) {
        return "";
    }

    // 其他方法实现...
}