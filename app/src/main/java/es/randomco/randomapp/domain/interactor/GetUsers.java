package es.randomco.randomapp.domain.interactor;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.executor.InteractorExecutor;
import es.randomco.randomapp.domain.executor.MainThreadExecutor;
import es.randomco.randomapp.domain.repository.Repository;

public class GetUsers extends Interactor {

    @NonNull
    private Repository mRepository;
    @NonNull
    private Callback<List<User>> mCallback;

    boolean mUserhasRequested;

    @Inject
    public GetUsers(InteractorExecutor interactorExecutor,
                    MainThreadExecutor mainThreadExecutor,
                    Repository repository) {
        super(interactorExecutor, mainThreadExecutor);
        mRepository = repository;
    }

    public void execute(boolean userHasRequested, @NonNull Callback<List<User>> callback) {
        mCallback = callback;
        mUserhasRequested = userHasRequested;

        executeInteractor();
    }

    @Override
    public void run() {
        try {
            List<User> users = mRepository.getUsers(mUserhasRequested);
            notifySuccess(users);
        } catch (Exception e) {
            notifyError(new Exception());
        }
    }

    private void notifySuccess(final List<User> users) {
        executeInMainThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(users);
            }
        });
    }

    private void notifyError(final Throwable throwable) {
        executeInMainThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onError(throwable);
            }
        });
    }
}
