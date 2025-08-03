package nc.db.config;

import com.zaxxer.hikari.HikariConfig;

import java.util.Objects;

public class DataSourceConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int maxPoolSize = 10;           // 默认最大连接数
    private int minIdle = 5;                // 默认最小空闲连接
    private long connectionTimeout = 30000; // 默认连接超时(ms)
    private long idleTimeout = 600000;      // 默认空闲超时(ms)
    private long maxLifetime = 1800000;     // 默认最大生命周期(ms)
    private String poolName = "default-pool"; // 连接池名称
    // 必须参数构造器
    public DataSourceConfig(String url, String username, String password) {
        this.url = Objects.requireNonNull(url, "Database URL cannot be null");
        this.username = Objects.requireNonNull(username, "Username cannot be null");
        this.password = Objects.requireNonNull(password, "Password cannot be null");
    }
    // 转换为HikariConfig（供DatabaseConnectionPool内部使用）
    public HikariConfig toHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        if (driverClassName != null) {
            config.setDriverClassName(driverClassName);
        }

        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(minIdle);
        config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setPoolName(poolName);

        // 生产环境推荐固定配置
        config.setLeakDetectionThreshold(5000); // 5秒泄漏检测
        config.setConnectionTestQuery("SELECT 1");
        config.setInitializationFailTimeout(-1); // 启动时连接失败永久重试

        return config;
    }
    // 参数验证方法
    public void validate() throws IllegalArgumentException {
        if (maxPoolSize < 1) {
            throw new IllegalArgumentException("Max pool size must be positive");
        }
        if (minIdle < 0 || minIdle > maxPoolSize) {
            throw new IllegalArgumentException("Min idle must be between 0 and maxPoolSize");
        }
        // 其他验证逻辑...
    }
    // ------------------- Builder模式 (可选) -------------------
    public static Builder builder(String url, String username, String password) {
        return new Builder(url, username, password);
    }
    public static class Builder {
        private final DataSourceConfig config;
        public Builder(String url, String username, String password) {
            this.config = new DataSourceConfig(url, username, password);
        }
        public Builder driverClassName(String driverClassName) {
            config.setDriverClassName(driverClassName);
            return this;
        }
        public Builder maxPoolSize(int maxPoolSize) {
            config.setMaxPoolSize(maxPoolSize);
            return this;
        }
        // 其他builder方法...
        public DataSourceConfig build() {
            config.validate();
            return config;
        }
    }
    // ------------------- Getter & Setter -------------------
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDriverClassName() {
        return driverClassName;
    }
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    public int getMinIdle() {
        return minIdle;
    }
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
    public long getConnectionTimeout() {
        return connectionTimeout;
    }
    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    public long getIdleTimeout() {
        return idleTimeout;
    }
    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }
    public long getMaxLifetime() {
        return maxLifetime;
    }
    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }
    public String getPoolName() {
        return poolName;
    }
    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }
}
