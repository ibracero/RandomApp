package es.randomco.randomapp.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.interactor.DeleteUser;
import es.randomco.randomapp.domain.interactor.FilterUsers;
import es.randomco.randomapp.domain.interactor.GetUsers;
import es.randomco.randomapp.domain.interactor.UpdateFavorite;
import es.randomco.randomapp.presentation.view.main.MainMvpView;

public class MainPresenter implements Presenter<MainMvpView> {

    private final GetUsers mGetUsers;
    private final UpdateFavorite mUpdateFavorite;
    private final DeleteUser mDeleteUser;
    private final FilterUsers mFilterUsers;
    private MainMvpView mView;

    private List<User> mUsers;

    @Inject
    public MainPresenter(GetUsers getUsers, UpdateFavorite updateFavorite, DeleteUser deleteUser, FilterUsers filterUsers) {
        mGetUsers = getUsers;
        mUpdateFavorite = updateFavorite;
        mDeleteUser = deleteUser;
        mFilterUsers = filterUsers;
    }

    @Override
    public void attachView(MainMvpView view) {
        mView = view;
        getUsers(false);
    }

    public void generateRandomUsers() {
        getUsers(true);
    }

    private void getUsers(boolean userHasRequested) {
        mView.showLoading();
        mGetUsers.execute(userHasRequested, new Interactor.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                mUsers = response;
                mView.hideLoading();
                mView.showUsers(response);
            }

            @Override
            public void onError(Throwable throwable) {
                mView.hideLoading();
                mView.showGetUsersError();
            }
        });
    }

    @Override
    public void detachView() {
    }

    public void onUserClick(User user) {
        mView.goToDetailActivity(user);
    }

    public void onUserChanged(User user) {
        mUpdateFavorite.execute(user, new Interactor.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
                mView.showUsers(mUsers);
            }

            @Override
            public void onError(Throwable throwable) {
                //Do nothing
            }
        });
    }

    public void deleteUser(final User user) {
        mDeleteUser.execute(user, new Interactor.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
                mUsers.remove(user);
                mView.showUsers(mUsers);
            }

            @Override
            public void onError(Throwable throwable) {
                mView.showDeleteUserError();
            }
        });
    }

    public void filter(String string) {
        mFilterUsers.execute(mUsers, string, new Interactor.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> response) {
                mView.showUsers(response);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    public List<User> getCurrentUserList() {
        return mUsers;
    }

    public MainMvpView getView() {
        return mView;
    }
}
