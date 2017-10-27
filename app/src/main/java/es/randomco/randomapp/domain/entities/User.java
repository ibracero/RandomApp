package es.randomco.randomapp.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String gender;
    private Name name;
    private Location address;
    private String registeredDate;
    private String email;
    private String phone;
    private ProfilePicture profilePicture;
    private boolean favorite;
    private double currentDistance;

    public User() {
    }

    protected User(Parcel in) {
        gender = in.readString();
        name = in.readParcelable(Name.class.getClassLoader());
        address = in.readParcelable(Location.class.getClassLoader());
        registeredDate = in.readString();
        email = in.readString();
        phone = in.readString();
        profilePicture = in.readParcelable(ProfilePicture.class.getClassLoader());
        favorite = in.readByte() != 0;
        currentDistance = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gender);
        dest.writeParcelable(name, flags);
        dest.writeParcelable(address, flags);
        dest.writeString(registeredDate);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeParcelable(profilePicture, flags);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeDouble(currentDistance);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public double getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(double currentDistance) {
        this.currentDistance = currentDistance;
    }

    @Override
    public boolean equals(Object obj) {
        User ob = (User) obj;
        return ob.getEmail().equals(this.getEmail());
    }
}
