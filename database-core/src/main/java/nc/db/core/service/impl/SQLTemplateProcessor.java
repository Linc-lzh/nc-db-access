package nc.db.core.service.impl;

import java.util.Map;

// SQLTemplateProcessor.java
public class SQLTemplateProcessor {
    public String processTemplate(String template, Map<String, Object> context) {
        // 简单实现，实际中可使用成熟的模板引擎
        String result = template;
        for (Map.Entry<String, Object> entry : context.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            String value = entry.getValue() != null ? entry.getValue().toString() : "NULL";
            result = result.replace(key, value);
        }
        return result;
    }
}

