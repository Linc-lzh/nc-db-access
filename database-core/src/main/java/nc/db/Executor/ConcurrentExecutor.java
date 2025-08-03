package nc.db.Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

// ConcurrentExecutor.java
public class ConcurrentExecutor {
    private final ExecutorService executorService;

    public ConcurrentExecutor(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public <T, R> List<R> executeInParallel(List<T> inputs, Function<T, R> task) {
        List<Future<R>> futures = new ArrayList<>();

        for (T input : inputs) {
            futures.add(executorService.submit(() -> task.apply(input)));
        }

        List<R> results = new ArrayList<>();
        for (Future<R> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Concurrent execution failed", e);
            }
        }

        return results;
    }
}

