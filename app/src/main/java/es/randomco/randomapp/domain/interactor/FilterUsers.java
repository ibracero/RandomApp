package es.randomco.randomapp.domain.interactor;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.executor.InteractorExecutor;
import es.randomco.randomapp.domain.executor.MainThreadExecutor;

public class FilterUsers extends Interactor {

    private List<User> mUsers;
    private String mFilter;
    private Callback<List<User>> mCallback;

    @Inject
    public FilterUsers(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        super(interactorExecutor, mainThreadExecutor);
    }

    public void execute(List<User> users, String filter, @NonNull Callback<List<User>> callback) {

        mUsers = users;
        mFilter = filter;
        mCallback = callback;

        executeInteractor();
    }

    @Override
    public void run() {

        List<User> filteredUsers = new ArrayList<>();

        for (User user : mUsers) {
            if (user.getName().getFirst().contains(mFilter)
                    || user.getName().getLast().contains(mFilter)
                    || user.getEmail().contains(mFilter)) {
                filteredUsers.add(user);
            }
        }

        showFiltered(filteredUsers);
    }

    private void showFiltered(final List<User> users) {
        executeInMainThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(users);
            }
        });
    }
}
