package es.randomco.randomapp.domain.interactor;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.executor.InteractorExecutor;
import es.randomco.randomapp.domain.executor.MainThreadExecutor;
import es.randomco.randomapp.domain.repository.Repository;

public class UpdateFavorite extends Interactor {

    @NonNull
    private Repository mRepository;

    @NonNull
    private Callback<Boolean> mCallback;

    private User mUser;


    @Inject
    public UpdateFavorite(InteractorExecutor interactorExecutor,
                          MainThreadExecutor mainThreadExecutor,
                          Repository repository) {
        super(interactorExecutor, mainThreadExecutor);
        mRepository = repository;
    }

    public void execute(User user, @NonNull Callback<Boolean> callback) {
        mCallback = callback;
        mUser = user;

        executeInteractor();
    }

    @Override
    public void run() {
        try {
            boolean updated = mRepository.updateFavorite(mUser);
            notifySuccess(updated);
        } catch (Exception e) {
            notifyError(new Exception());
        }
    }

    private void notifySuccess(final Boolean updated) {
        executeInMainThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(updated);
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