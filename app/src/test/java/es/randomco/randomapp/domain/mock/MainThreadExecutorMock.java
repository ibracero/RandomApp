package es.randomco.randomapp.domain.mock;

import es.randomco.randomapp.domain.executor.MainThreadExecutor;

public class MainThreadExecutorMock implements MainThreadExecutor {

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}