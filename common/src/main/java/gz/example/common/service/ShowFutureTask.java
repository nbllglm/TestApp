package gz.example.common.service;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ShowFutureTask<V> extends FutureTask<V> {
    public ShowFutureTask(Callable<V> callable) {
        super(callable);
    }

    public ShowFutureTask(Runnable runnable, V result) {
        super(runnable, result);
    }
}
