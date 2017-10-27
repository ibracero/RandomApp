package es.randomco.randomapp.domain.repository.datasource;

import java.util.List;

import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.exceptions.NetworkException;

public interface NetworkDataSource {

    List<User> getUsers() throws NetworkException;

}
