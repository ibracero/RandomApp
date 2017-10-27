package es.randomco.randomapp.data.datasource.db.models.mapper;

import javax.inject.Inject;

import es.randomco.randomapp.data.datasource.db.models.DbLocation;
import es.randomco.randomapp.data.datasource.db.models.DbName;
import es.randomco.randomapp.data.datasource.db.models.DbProfilePicture;
import es.randomco.randomapp.data.datasource.db.models.DbUser;
import es.randomco.randomapp.domain.entities.Location;
import es.randomco.randomapp.domain.entities.Name;
import es.randomco.randomapp.domain.entities.ProfilePicture;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.mappers.Mapper;

public class DbUserMapper implements Mapper<DbUser, User> {

    @Inject
    public DbUserMapper() {
    }

    @Override
    public User map(DbUser model) {

        User user = new User();
        user.setGender(model.getGender());
        if (model.getName() != null) {
            user.setName(new Name(model.getName().getTitle(),
                    model.getName().getFirst(),
                    model.getName().getLast()));
        }
        user.setEmail(model.getEmail());
        user.setPhone(model.getPhone());
        user.setRegisteredDate(model.getRegisteredDate());
        if (model.getAddress() != null) {
            user.setAddress(new Location(model.getAddress().getStreet(),
                    model.getAddress().getCity(),
                    model.getAddress().getState(),
                    model.getAddress().getPostcode(),
                    model.getAddress().getLat(),
                    model.getAddress().getLon()));
        }
        if (model.getProfilePicture() != null) {
            user.setProfilePicture(new ProfilePicture(
                    model.getProfilePicture().getLarge(),
                    model.getProfilePicture().getMedium(),
                    model.getProfilePicture().getThumbnail()
            ));
        }
        user.setFavorite(model.isFavorite());

        return user;
    }

    @Override
    public DbUser inverseMap(User model) {

        DbUser dbUser = new DbUser();

        dbUser.setGender(model.getGender());
        if (model.getName() != null) {
            dbUser.setName(new DbName(model.getName().getTitle(),
                    model.getName().getFirst(),
                    model.getName().getLast()));
        }
        dbUser.setEmail(model.getEmail());
        dbUser.setPhone(model.getPhone());
        dbUser.setRegisteredDate(model.getRegisteredDate());
        if (model.getAddress() != null) {
            dbUser.setLocation(new DbLocation(model.getAddress().getStreet(),
                    model.getAddress().getCity(),
                    model.getAddress().getState(),
                    model.getAddress().getPostcode(),
                    model.getAddress().getLat(),
                    model.getAddress().getLon()));
        }
        if (model.getProfilePicture() != null) {
            dbUser.setProfilePicture(new DbProfilePicture(
                    model.getProfilePicture().getLarge(),
                    model.getProfilePicture().getMedium(),
                    model.getProfilePicture().getThumbnail()
            ));
        }
        dbUser.setFavorite(model.isFavorite());

        return dbUser;
    }
}
