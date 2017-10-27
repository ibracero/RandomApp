package es.randomco.randomapp.domain.interactor;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.executor.InteractorExecutor;
import es.randomco.randomapp.domain.executor.MainThreadExecutor;
import es.randomco.randomapp.utils.Utils;

public class FilterNearbyUsers extends Interactor {

    private List<User> mUsers;
    private double mLat, mLon;
    private Callback<List<User>> mCallback;

    @Inject
    public FilterNearbyUsers(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        super(interactorExecutor, mainThreadExecutor);
    }

    public void execute(List<User> users, double currentLatitude, double currentLongitude, @NonNull Callback<List<User>> callback) {

        mUsers = users;
        mLat = currentLatitude;
        mLon = currentLongitude;
        mCallback = callback;

        executeInteractor();
    }

    @Override
    public void run() {

        List<User> filteredUsers = new ArrayList<>();

        for (User user : mUsers) {
            if (Utils.distanceBetweenPoints(mLat, mLon, user.getAddress().getLat(), user.getAddress().getLon()) <= 10) {
                user.setCurrentDistance(Utils.distanceBetweenPoints(mLat, mLon, user.getAddress().getLat(), user.getAddress().getLon()));
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
