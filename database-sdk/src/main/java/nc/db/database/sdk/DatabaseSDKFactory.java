package nc.db.database.sdk;

// SDK工厂类
public class DatabaseSDKFactory {
    public static DatabaseSDK createSDK(DataSourceConfig config) {
        return new DatabaseSDKImpl(config);
    }
}
