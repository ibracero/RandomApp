package es.randomco.randomapp.data.datasource.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "DbLocation")
public class DbLocation {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String street;

    @DatabaseField
    private String city;

    @DatabaseField
    private String state;

    @DatabaseField
    private String postcode;

    @DatabaseField
    private double lat;

    @DatabaseField
    private double lon;

    public DbLocation() {
    }

    public DbLocation(String street, String city, String state, String postcode, double lat, double lon) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.lat = lat;
        this.lon = lon;
    }

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
}
