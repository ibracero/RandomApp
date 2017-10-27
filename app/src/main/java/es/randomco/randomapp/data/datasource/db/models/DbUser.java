package es.randomco.randomapp.data.datasource.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import es.randomco.randomapp.domain.entities.User;

@DatabaseTable(tableName = "DbUser")
public class DbUser {

    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_FAVORITE = "favorite";
    public static final String FIELD_DELETED = "deleted";

    @DatabaseField
    private String gender;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private DbName name;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private DbLocation location;

    @DatabaseField
    private String registeredDate;

    @DatabaseField(id = true)
    private String email;

    @DatabaseField
    private String phone;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private DbProfilePicture profilePicture;

    @DatabaseField
    private boolean favorite;

    @DatabaseField
    private boolean deleted;

    public DbUser() {
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(DbName name) {
        this.name = name;
    }

    public void setLocation(DbLocation location) {
        this.location = location;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicture(DbProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public DbName getName() {
        return name;
    }

    public DbLocation getAddress() {
        return location;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public String getEmail() {
        return email;
    }

    public DbProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public DbLocation getLocation() {
        return location;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object obj) {
        DbUser ob = (DbUser) obj;
        return ob.getEmail().equals(this.getEmail());
    }
}
