package es.randomco.randomapp.domain.executor;

public interface MainThreadExecutor {
    void execute(Runnable runnable);
}