package es.randomco.randomapp.presentation.presenter;


import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.interactor.FilterNearbyUsers;
import es.randomco.randomapp.presentation.view.nearby.NearbyMvpView;

public class NearbyPresenter implements Presenter<NearbyMvpView> {

    private final FilterNearbyUsers mFilterNearbyUsers;
    private NearbyMvpView mView;

    @Inject
    public NearbyPresenter(FilterNearbyUsers filterNearbyUsers) {
        mFilterNearbyUsers = filterNearbyUsers;
    }

    @Override
    public void attachView(NearbyMvpView view) {
        mView = view;
    }

    @Override
    public void detachView() {

    }

    public void filterByDistance(List<User> mNearbyUserList, double latitude, double longitude) {
        mFilterNearbyUsers.execute(mNearbyUserList, latitude, longitude, new Interactor.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                mView.showNearbyUsers(response);
            }

            @Override
            public void onError(Throwable throwable) {
                mView.showError();
            }
        });
    }

    public Location getCurrentLocation(Context context) throws SecurityException {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public NearbyMvpView getView() {
        return mView;
    }
}
