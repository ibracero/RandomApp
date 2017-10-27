package es.randomco.randomapp.domain.repository.datasource;

import java.util.List;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.exceptions.DbException;

public interface DbDataSource {

    List<User> getUsers() throws DbException;

    void saveUsers(List<User> users) throws DbException;

    void updateFavorite(User user);

    void deleteUser(User user);
}
