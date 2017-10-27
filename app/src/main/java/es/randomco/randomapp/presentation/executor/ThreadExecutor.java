package es.randomco.randomapp.presentation.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.executor.InteractorExecutor;


public class ThreadExecutor implements InteractorExecutor {

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();
    private ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public ThreadExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TIME_UNIT, WORK_QUEUE);
    }

    @Override
    public void run(Interactor interactor) {
        if (interactor != null) {
            threadPoolExecutor.submit(interactor);
        }
    }
}