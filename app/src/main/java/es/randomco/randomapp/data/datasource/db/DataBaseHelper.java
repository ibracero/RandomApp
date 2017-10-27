package es.randomco.randomapp.data.datasource.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import es.randomco.randomapp.data.datasource.db.models.DbLocation;
import es.randomco.randomapp.data.datasource.db.models.DbName;
import es.randomco.randomapp.data.datasource.db.models.DbProfilePicture;
import es.randomco.randomapp.data.datasource.db.models.DbUser;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TAG = DataBaseHelper.class.getSimpleName();

    public DataBaseHelper(String databaseName, final Context context) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DbName.class);
            TableUtils.createTable(connectionSource, DbProfilePicture.class);
            TableUtils.createTable(connectionSource, DbLocation.class);
            TableUtils.createTable(connectionSource, DbUser.class);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to create tables", e);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DbName.class, true);
            TableUtils.dropTable(connectionSource, DbProfilePicture.class, true);
            TableUtils.dropTable(connectionSource, DbLocation.class, true);
            TableUtils.dropTable(connectionSource, DbUser.class, true);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to drop tables", e);
        }
    }

    public Dao<DbUser, String> getDbUserDao() {
        try {
            return getDao(DbUser.class);
        } catch (SQLException e) {
            Log.e(TAG, "getDbUserDao", e);

        }
        return null;
    }
}