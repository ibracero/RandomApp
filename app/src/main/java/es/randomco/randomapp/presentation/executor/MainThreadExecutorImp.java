package es.randomco.randomapp.presentation.executor;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;

import es.randomco.randomapp.domain.executor.MainThreadExecutor;

public class MainThreadExecutorImp implements MainThreadExecutor {

    private Handler handler;

    @Inject
    public MainThreadExecutorImp() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}