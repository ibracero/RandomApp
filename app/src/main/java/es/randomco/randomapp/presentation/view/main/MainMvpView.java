package es.randomco.randomapp.presentation.view.main;

import java.util.List;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.view.MvpView;

public interface MainMvpView extends MvpView {

    void showUsers(List<User> users);

    void showGetUsersError();

    void goToDetailActivity(User user);

    void showDeleteUserError();
}
