package es.randomco.randomapp.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.randomco.randomapp.domain.executor.InteractorExecutor;
import es.randomco.randomapp.domain.executor.MainThreadExecutor;
import es.randomco.randomapp.presentation.executor.MainThreadExecutorImp;
import es.randomco.randomapp.presentation.executor.ThreadExecutor;

@Module()
public class ExecutorModule {

    @Provides
    @Singleton
    public InteractorExecutor providesInteractroExecutor(ThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    @Singleton
    public MainThreadExecutor providesMainThreadExecutor(MainThreadExecutorImp mainThreadImp) {
        return mainThreadImp;
    }
}
