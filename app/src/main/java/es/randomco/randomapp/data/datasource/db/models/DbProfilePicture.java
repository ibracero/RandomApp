package es.randomco.randomapp.data.datasource.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "DbProfilePicture")
public class DbProfilePicture {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    String large;

    @DatabaseField
    String medium;

    @DatabaseField
    String thumbnail;

    public DbProfilePicture() {
    }

    public DbProfilePicture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

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
