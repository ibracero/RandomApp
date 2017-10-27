package es.randomco.randomapp;

import es.randomco.randomapp.data.datasource.db.models.DbLocation;
import es.randomco.randomapp.data.datasource.db.models.DbName;
import es.randomco.randomapp.data.datasource.db.models.DbProfilePicture;
import es.randomco.randomapp.data.datasource.db.models.DbUser;
import es.randomco.randomapp.data.datasource.network.models.ApiLocation;
import es.randomco.randomapp.data.datasource.network.models.ApiName;
import es.randomco.randomapp.data.datasource.network.models.ApiProfilePicture;
import es.randomco.randomapp.data.datasource.network.models.ApiUser;
import es.randomco.randomapp.domain.entities.Location;
import es.randomco.randomapp.domain.entities.Name;
import es.randomco.randomapp.domain.entities.ProfilePicture;
import es.randomco.randomapp.domain.entities.User;

public class DataHelper {

    private DataHelper() {
    }

    public static User getUser(String seed) {
        User user = new User();

        user.setGender("male");
        user.setName(new Name(seed, seed, seed));
        user.setAddress(new Location(seed, seed, seed, seed, -30, -40));
        user.setEmail(seed + "@dot.com");
        user.setPhone(seed + "23456778");
        user.setFavorite(true);
        user.setCurrentDistance(seed.length());
        user.setRegisteredDate(seed);
        user.setProfilePicture(new ProfilePicture("https://randomuser.me/api/portraits/med/men/41.jpg",
                "https://randomuser.me/api/portraits/med/men/41.jpg",
                "https://randomuser.me/api/portraits/med/men/41.jpg"));

        return user;
    }

    public static DbUser getDbUser(String seed) {
        DbUser user = new DbUser();

        user.setGender("male");
        user.setName(new DbName(seed, seed, seed));
        user.setLocation(new DbLocation(seed, seed, seed, seed, -30, -40));
        user.setEmail(seed + "@dot.com");
        user.setPhone(seed + "23456778");
        user.setProfilePicture(new DbProfilePicture("https://randomuser.me/api/portraits/med/men/41.jpg",
                "https://randomuser.me/api/portraits/med/men/41.jpg",
                "https://randomuser.me/api/portraits/med/men/41.jpg"));
        user.setFavorite(true);
        user.setDeleted(false);
        user.setRegisteredDate(seed);

        return user;
    }

    public static ApiUser getApiUser(String seed) {
        ApiUser user = new ApiUser();

        user.setGender("male");
        user.setName(new ApiName(seed, seed, seed));
        user.setLocation(new ApiLocation(seed, seed, seed, seed));
        user.setEmail(seed + "@dot.com");
        user.setPhone(seed + "23456778");
        user.setRegisteredDate(seed);
        user.setProfilePicture(new ApiProfilePicture("https://randomuser.me/api/portraits/med/men/41.jpg",
                "https://randomuser.me/api/portraits/med/men/41.jpg",
                "https://randomuser.me/api/portraits/med/men/41.jpg"));

        return user;
    }
}
