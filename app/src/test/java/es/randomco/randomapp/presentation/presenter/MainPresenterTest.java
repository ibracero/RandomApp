package es.randomco.randomapp.presentation.presenter;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import es.randomco.randomapp.DataHelper;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.exceptions.NetworkException;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.interactor.DeleteUser;
import es.randomco.randomapp.domain.interactor.FilterUsers;
import es.randomco.randomapp.domain.interactor.GetUsers;
import es.randomco.randomapp.domain.interactor.UpdateFavorite;
import es.randomco.randomapp.presentation.view.main.MainMvpView;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Test
    public void shouldInitialicePresenter() {
        MainPresenter mainPresenter = getMainPresenter(getGetUsers(40));

        verify(mainPresenter.getView()).showLoading();
        verify(mainPresenter.getView()).hideLoading();
        verify(mainPresenter.getView()).showUsers(ArgumentMatchers.<User>anyList());

    }

    @Test
    public void shouldInitialicePresenterFail() {
        MainPresenter mainPresenter = getMainPresenter(getGetUsersFail());

        verify(mainPresenter.getView()).showLoading();
        verify(mainPresenter.getView()).hideLoading();
        verify(mainPresenter.getView()).showGetUsersError();
    }

    @Test
    public void shouldGoToDetailsWhenUserClick() {
        MainPresenter mainPresenter = getMainPresenter();

        User user = DataHelper.getUser("1");

        mainPresenter.onUserClick(user);

        verify(mainPresenter.getView()).goToDetailActivity(user);
    }

    @Test
    public void shouldRefreshUserListAfterUserDeletionIfRequestSuccess() {
        MainPresenter mainPresenter = getMainPresenter(getDeleteUserSuccess());

        User user = DataHelper.getUser("1");
        mainPresenter.deleteUser(user);
        verify(mainPresenter.getView(), times(2)).showUsers(ArgumentMatchers.<User>anyList());
    }

    @Test
    public void shouldRefreshUserListAfterUserDeletionIfRequestFail() {
        MainPresenter mainPresenter = getMainPresenter(getDeleteUserFail());

        User user = DataHelper.getUser("1");
        mainPresenter.deleteUser(user);
        verify(mainPresenter.getView()).showDeleteUserError();
    }


    private MainPresenter getMainPresenter() {
        MainPresenter mainPresenter = new MainPresenter(getGetUsers(40), getUpdateFavorite(), getDeleteUserSuccess(), getFilterUsers());
        mainPresenter.attachView(mock(MainMvpView.class));

        return mainPresenter;
    }

    private MainPresenter getMainPresenter(GetUsers getUsers) {
        MainPresenter mainPresenter = new MainPresenter(getUsers, getUpdateFavorite(), getDeleteUserSuccess(), getFilterUsers());
        mainPresenter.attachView(mock(MainMvpView.class));

        return mainPresenter;
    }


    private MainPresenter getMainPresenter(DeleteUser deleteUser) {
        MainPresenter mainPresenter = new MainPresenter(getGetUsers(40), getUpdateFavorite(), deleteUser, getFilterUsers());
        mainPresenter.attachView(mock(MainMvpView.class));

        return mainPresenter;
    }


    private GetUsers getGetUsers(final int itemCount) {

        GetUsers getUsers = mock(GetUsers.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                List<User> users = new ArrayList<User>();

                for (int i = 0; i < itemCount; i++) {
                    users.add(DataHelper.getUser(String.valueOf(i)));
                }

                ((Interactor.Callback<List<User>>) invocation.getArguments()[1]).onSuccess(users);
                return null;
            }
        }).when(getUsers).execute(anyBoolean(), any(Interactor.Callback.class));

        return getUsers;
    }

    private GetUsers getGetUsersFail() {

        GetUsers getUsers = mock(GetUsers.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                ((Interactor.Callback<List<User>>) invocation.getArguments()[1]).onError(new NetworkException("", ""));
                return null;
            }
        }).when(getUsers).execute(anyBoolean(), any(Interactor.Callback.class));

        return getUsers;
    }

    private UpdateFavorite getUpdateFavorite() {

        UpdateFavorite updateFavorite = mock(UpdateFavorite.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Interactor.Callback<Boolean>) invocation.getArguments()[1]).onSuccess(true);
                return null;
            }
        }).when(updateFavorite).execute(any(User.class), any(Interactor.Callback.class));

        return updateFavorite;
    }

    private DeleteUser getDeleteUserSuccess() {

        DeleteUser deleteUser = mock(DeleteUser.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Interactor.Callback<Boolean>) invocation.getArguments()[1]).onSuccess(true);
                return null;
            }
        }).when(deleteUser).execute(any(User.class), any(Interactor.Callback.class));

        return deleteUser;
    }

    private DeleteUser getDeleteUserFail() {

        DeleteUser deleteUser = mock(DeleteUser.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Interactor.Callback<Boolean>) invocation.getArguments()[1]).onError(new Throwable());
                return null;
            }
        }).when(deleteUser).execute(any(User.class), any(Interactor.Callback.class));

        return deleteUser;
    }

    private FilterUsers getFilterUsers() {

        FilterUsers deleteUser = mock(FilterUsers.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                List<User> users = new ArrayList<User>();

                for (int i = 0; i < 5; i++) {
                    users.add(DataHelper.getUser(String.valueOf(i)));
                }

                ((Interactor.Callback<List<User>>) invocation.getArguments()[2]).onSuccess(users);
                return null;
            }
        }).when(deleteUser).execute(any(List.class), any(String.class), any(Interactor.Callback.class));

        return deleteUser;
    }


}