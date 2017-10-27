package es.randomco.randomapp.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfilePicture implements Parcelable {

    String large;
    String medium;
    String thumbnail;

    public ProfilePicture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    protected ProfilePicture(Parcel in) {
        this.large = in.readString();
        this.medium = in.readString();
        this.thumbnail = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.large);
        dest.writeString(this.medium);
        dest.writeString(this.thumbnail);
    }

    public static final Parcelable.Creator<ProfilePicture> CREATOR = new Parcelable.Creator<ProfilePicture>() {
        @Override
        public ProfilePicture createFromParcel(Parcel source) {
            return new ProfilePicture(source);
        }

        @Override
        public ProfilePicture[] newArray(int size) {
            return new ProfilePicture[size];
        }
    };

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
