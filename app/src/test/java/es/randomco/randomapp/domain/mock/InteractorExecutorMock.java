package es.randomco.randomapp.domain.mock;

import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.executor.InteractorExecutor;

public class InteractorExecutorMock implements InteractorExecutor {

    @Override
    public void run(Interactor interactor) {
        interactor.run();
    }
}