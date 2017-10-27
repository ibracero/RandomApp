package es.randomco.randomapp.presentation.presenter;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import es.randomco.randomapp.DataHelper;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.executor.Interactor;
import es.randomco.randomapp.domain.interactor.FilterNearbyUsers;
import es.randomco.randomapp.presentation.view.nearby.NearbyMvpView;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NearbyPresenterTest {

    @Test
    public void shouldReturnFilteredUsersToUiOnSuccess() {
        NearbyPresenter presenter = getNearbyPresenter(false);

        presenter.filterByDistance(getMockUsers(), -4, 40);

        verify(presenter.getView()).showNearbyUsers(ArgumentMatchers.<User>anyList());
    }

    @Test
    public void shouldShowErrorMessageOnFilteredFail() {
        NearbyPresenter presenter = getNearbyPresenter(true);

        presenter.filterByDistance(getMockUsers(), -4, 40);

        verify(presenter.getView()).showError();
    }

    private NearbyPresenter getNearbyPresenter(boolean error) {
        NearbyPresenter presenter;

        if (!error) {
            presenter = new NearbyPresenter(getFilterNearbyUsersSuccess());
        } else {
            presenter = new NearbyPresenter(getFilterNearbyUsersFail());
        }
        presenter.attachView(mock(NearbyMvpView.class));

        return presenter;
    }

    public FilterNearbyUsers getFilterNearbyUsersSuccess() {
        FilterNearbyUsers filterNearbyUsers = mock(FilterNearbyUsers.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                List<User> users = getMockUsers();

                ((Interactor.Callback<List<User>>) invocation.getArguments()[3]).onSuccess(users);
                return null;
            }
        }).when(filterNearbyUsers).execute(ArgumentMatchers.<User>anyList(), anyDouble(), anyDouble(), any(Interactor.Callback.class));

        return filterNearbyUsers;
    }

    public FilterNearbyUsers getFilterNearbyUsersFail() {
        FilterNearbyUsers filterNearbyUsers = mock(FilterNearbyUsers.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                ((Interactor.Callback<List<User>>) invocation.getArguments()[3]).onError(new Exception());
                return null;
            }
        }).when(filterNearbyUsers).execute(ArgumentMatchers.<User>anyList(), anyDouble(), anyDouble(), any(Interactor.Callback.class));

        return filterNearbyUsers;
    }

    public List<User> getMockUsers() {
        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 10; i++) {
            users.add(DataHelper.getUser(String.valueOf(i)));
        }

        return users;
    }
}