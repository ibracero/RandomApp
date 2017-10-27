package es.randomco.randomapp.domain.repository;

import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.exceptions.DbException;
import es.randomco.randomapp.domain.repository.datasource.DbDataSource;
import es.randomco.randomapp.domain.repository.datasource.NetworkDataSource;
import es.randomco.randomapp.utils.Utils;

public class Repository {

    private final NetworkDataSource mNetworkDataSource;
    private final DbDataSource mDbDataSource;

    @Inject
    public Repository(NetworkDataSource networkDataSource, DbDataSource dbDataSource) {
        mNetworkDataSource = networkDataSource;
        mDbDataSource = dbDataSource;
    }

    public List<User> getUsers(boolean mUserhasRequested) {

        if (mUserhasRequested) {
            List<User> users = mNetworkDataSource.getUsers();

            try {
                mDbDataSource.saveUsers(users);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

        return mDbDataSource.getUsers();
    }

    public boolean updateFavorite(User user) {
        try {
            mDbDataSource.updateFavorite(user);
            return true;

        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        try {
            mDbDataSource.deleteUser(user);
            return true;

        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }
    }
}
