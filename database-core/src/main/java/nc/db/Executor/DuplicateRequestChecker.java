package nc.db.Executor;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

// DuplicateRequestChecker.java
public class DuplicateRequestChecker {
    private final Cache<String, Boolean> requestCache;

    public DuplicateRequestChecker() {
        this.requestCache = Caffeine.newBuilder()
                .maximumSize(100000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    public boolean isDuplicate(String requestId) {
        Boolean isPresent = requestCache.getIfPresent(requestId);
        if (isPresent != null && isPresent) {
            return true;
        }

        requestCache.put(requestId, true);
        return false;
    }
}

