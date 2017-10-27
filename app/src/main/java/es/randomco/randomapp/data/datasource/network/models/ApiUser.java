package es.randomco.randomapp.data.datasource.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiUser {

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("name")
    @Expose
    private ApiName name;

    @SerializedName("location")
    @Expose
    private ApiLocation location;

    @SerializedName("registered")
    @Expose
    private String registeredDate;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("picture")
    @Expose
    private ApiProfilePicture profilePicture;

    public String getGender() {
        return gender;
    }

    public ApiName getName() {
        return name;
    }

    public ApiLocation getAddress() {
        return location;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public String getEmail() {
        return email;
    }

    public ApiLocation getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ApiProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(ApiName name) {
        this.name = name;
    }

    public void setLocation(ApiLocation location) {
        this.location = location;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicture(ApiProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public boolean equals(Object obj) {
        ApiUser ob = (ApiUser) obj;
        return ob.getEmail().equals(this.getEmail());
    }
}
