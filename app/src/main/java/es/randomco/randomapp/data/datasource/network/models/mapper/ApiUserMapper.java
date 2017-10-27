package es.randomco.randomapp.data.datasource.network.models.mapper;

import javax.inject.Inject;

import es.randomco.randomapp.data.datasource.network.models.ApiLocation;
import es.randomco.randomapp.data.datasource.network.models.ApiName;
import es.randomco.randomapp.data.datasource.network.models.ApiProfilePicture;
import es.randomco.randomapp.data.datasource.network.models.ApiUser;
import es.randomco.randomapp.domain.entities.Location;
import es.randomco.randomapp.domain.entities.Name;
import es.randomco.randomapp.domain.entities.ProfilePicture;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.mappers.Mapper;
import es.randomco.randomapp.utils.Utils;

public class ApiUserMapper implements Mapper<ApiUser, User> {

    @Inject
    public ApiUserMapper() {
    }

    @Override
    public User map(ApiUser model) {

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
                    Utils.generateRandomLatitudeInMadrid(),
                    Utils.generateRandomLongitudeInMadrid()));
        }
        if (model.getProfilePicture() != null) {
            user.setProfilePicture(new ProfilePicture(
                    model.getProfilePicture().getLarge(),
                    model.getProfilePicture().getMedium(),
                    model.getProfilePicture().getThumbnail()
            ));
        }

        return user;
    }

    @Override
    public ApiUser inverseMap(User model) {

        ApiUser apiUser = new ApiUser();

        apiUser.setGender(model.getGender());
        if (model.getName() != null) {
            apiUser.setName(new ApiName(model.getName().getTitle(),
                    model.getName().getFirst(),
                    model.getName().getLast()));
        }
        apiUser.setEmail(model.getEmail());
        apiUser.setPhone(model.getPhone());
        apiUser.setRegisteredDate(model.getRegisteredDate());
        if (model.getAddress() != null) {
            apiUser.setLocation(new ApiLocation(model.getAddress().getStreet(),
                    model.getAddress().getCity(),
                    model.getAddress().getState(),
                    model.getAddress().getPostcode()));
        }
        if (model.getProfilePicture() != null) {
            apiUser.setProfilePicture(new ApiProfilePicture(
                    model.getProfilePicture().getLarge(),
                    model.getProfilePicture().getMedium(),
                    model.getProfilePicture().getThumbnail()
            ));
        }

        return apiUser;
    }
}
