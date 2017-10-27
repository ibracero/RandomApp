package es.randomco.randomapp.presentation.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.randomco.randomapp.data.datasource.db.DataBaseHelper;
import es.randomco.randomapp.data.datasource.db.DbDataSourceImpl;
import es.randomco.randomapp.domain.repository.datasource.DbDataSource;
import es.randomco.randomapp.presentation.di.qualifiers.DatabaseName;

@Module
public class DbModule {

    @Provides
    @Singleton
    public DbDataSource provideDbDataSource(DbDataSourceImpl dbDataSourceImp) {
        return dbDataSourceImp;
    }

    @Provides
    @Singleton
    public DataBaseHelper provideDatabaseHelper(@DatabaseName String databaseName, Application app) {
        return new DataBaseHelper(databaseName, app);
    }

    @Provides
    @Singleton
    @DatabaseName
    public String provideDatabaseName() {
        return "randomco.db";
    }
}
