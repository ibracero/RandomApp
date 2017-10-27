package es.randomco.randomapp.domain.interactor;

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
import es.randomco.randomapp.domain.executor.InteractorExecutor;
import es.randomco.randomapp.domain.executor.MainThreadExecutor;
import es.randomco.randomapp.domain.mock.InteractorExecutorMock;
import es.randomco.randomapp.domain.mock.MainThreadExecutorMock;
import es.randomco.randomapp.domain.repository.Repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetUsersTest {

    @Test
    public void shouldReturnListToPresenterOnSuccess() {

        GetUsers getUsers = getGetUsers();
        Interactor.Callback<List<User>> callback = mock(Interactor.Callback.class);

        getUsers.execute(anyBoolean(), callback);

        verify(callback).onSuccess(ArgumentMatchers.<User>anyList());
    }

    @Test
    public void shouldReturnAnErrorToPresenter() {

        GetUsers getUsers = getGetUsersFail();
        Interactor.Callback<List<User>> callback = mock(Interactor.Callback.class);

        getUsers.execute(anyBoolean(), callback);

        verify(callback).onError(any(Throwable.class));
    }

    private GetUsers getGetUsers() {

        InteractorExecutor interactorExecutor = new InteractorExecutorMock();
        MainThreadExecutor mainThreadExecutor = new MainThreadExecutorMock();
        Repository repository = mock(Repository.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return getMockUsers();
            }
        }).when(repository).getUsers(anyBoolean());

        return new GetUsers(interactorExecutor, mainThreadExecutor, repository);

    }

    private GetUsers getGetUsersFail() {

        InteractorExecutor interactorExecutor = new InteractorExecutorMock();
        MainThreadExecutor mainThreadExecutor = new MainThreadExecutorMock();
        Repository repository = mock(Repository.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                throw new NetworkException("", "");
            }
        }).when(repository).getUsers(anyBoolean());

        return new GetUsers(interactorExecutor, mainThreadExecutor, repository);

    }


    public List<User> getMockUsers() {
        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 10; i++) {
            users.add(DataHelper.getUser(String.valueOf(i)));
        }

        return users;
    }
}