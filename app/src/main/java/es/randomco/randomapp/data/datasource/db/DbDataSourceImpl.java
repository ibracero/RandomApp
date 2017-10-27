package es.randomco.randomapp.data.datasource.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.data.datasource.db.models.DbUser;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.exceptions.DbException;
import es.randomco.randomapp.domain.mappers.ListMapper;
import es.randomco.randomapp.domain.repository.datasource.DbDataSource;
import es.randomco.randomapp.utils.constant.Errors;

public class DbDataSourceImpl implements DbDataSource {

    private final Dao<DbUser, String> mUserDao;
    private final ListMapper<DbUser, User> mListDbUserMapper;

    @Inject
    public DbDataSourceImpl(DataBaseHelper dataBaseHelper, ListMapper<DbUser, User> dbListUserMapper) {
        mUserDao = dataBaseHelper.getDbUserDao();
        mListDbUserMapper = dbListUserMapper;
    }

    @Override
    public List<User> getUsers() throws DbException {

        try {
            List<DbUser> dbUsers = mUserDao.queryBuilder().where().eq(DbUser.FIELD_DELETED, false).query();

            return mListDbUserMapper.map(dbUsers);
        } catch (SQLException e) {
            throw new DbException(Errors.UNKOWN, e.getMessage());
        }
    }

    @Override
    public void saveUsers(List<User> users) throws DbException {

        List<DbUser> dbUsers = mListDbUserMapper.inverseMap(users);

        try {
            for (DbUser dbUser : dbUsers) {
                mUserDao.createIfNotExists(dbUser);
            }
        } catch (SQLException e) {
            throw new DbException(Errors.UNKOWN, e.getMessage());
        }
    }

    @Override
    public void updateFavorite(User user) throws DbException {
        try {
            UpdateBuilder<DbUser, String> updateBuilder = mUserDao.updateBuilder();
            updateBuilder.where().eq(DbUser.FIELD_EMAIL, user.getEmail());
            updateBuilder.updateColumnValue(DbUser.FIELD_FAVORITE, user.isFavorite());
            updateBuilder.update();

        } catch (SQLException e) {
            throw new DbException(Errors.UNKOWN, e.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            UpdateBuilder<DbUser, String> updateBuilder = mUserDao.updateBuilder();
            updateBuilder.where().eq(DbUser.FIELD_EMAIL, user.getEmail());
            updateBuilder.updateColumnValue(DbUser.FIELD_DELETED, true);
            updateBuilder.update();

        } catch (SQLException e) {
            throw new DbException(Errors.UNKOWN, e.getMessage());
        }
    }


}
