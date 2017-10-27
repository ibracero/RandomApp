package es.randomco.randomapp.data.datasource.network;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.data.datasource.network.models.ApiUser;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.exceptions.NetworkException;
import es.randomco.randomapp.domain.mappers.ListMapper;
import es.randomco.randomapp.domain.repository.datasource.NetworkDataSource;
import es.randomco.randomapp.utils.constant.Errors;

public class NetworkDataSourceImpl implements NetworkDataSource {

    private final ApiService mApiService;
    private final ListMapper<ApiUser, User> mUserListMapper;

    @Inject
    public NetworkDataSourceImpl(ApiService apiService, ListMapper<ApiUser, User> userListMapper) {
        mApiService = apiService;
        mUserListMapper = userListMapper;
    }

    @Override
    public List<User> getUsers() throws NetworkException {
        try {
            List<ApiUser> apiUsers = mApiService.getUsers().execute().body().getResults();

            return mUserListMapper.map(apiUsers);
        } catch (IOException exception) {
            throw new NetworkException(Errors.UNKOWN, exception.getMessage());
        }
    }
}
