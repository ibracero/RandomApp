package es.randomco.randomapp.presentation.view.nearby;

import java.util.List;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.view.MvpView;

public interface NearbyMvpView extends MvpView {
    void showNearbyUsers(List<User> response);

    void showError();
}
