package es.randomco.randomapp.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    private String street;
    private String city;
    private String state;
    private String postcode;
    private double lat;
    private double lon;

    public Location(String street, String city, String state, String postcode, double lat, double lon) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.lat = lat;
        this.lon = lon;
    }

    protected Location(Parcel in) {
        street = in.readString();
        city = in.readString();
        state = in.readString();
        postcode = in.readString();
        lat = in.readDouble();
        lon = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(postcode);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return street.concat(", ").concat(city).concat(" (").concat(state).concat(")").concat(" - ").concat(postcode);
    }
}
