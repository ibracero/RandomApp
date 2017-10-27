package es.randomco.randomapp.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.randomco.randomapp.data.datasource.db.models.DbUser;
import es.randomco.randomapp.data.datasource.db.models.mapper.DbUserMapper;
import es.randomco.randomapp.data.datasource.network.models.ApiUser;
import es.randomco.randomapp.data.datasource.network.models.mapper.ApiUserMapper;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.domain.mappers.ListMapper;
import es.randomco.randomapp.domain.mappers.Mapper;

@Module
public class DataModule {

    @Provides
    @Singleton
    public ListMapper<ApiUser, User> provideApiUserMapper(ApiUserMapper apiUserMapper) {
        return new ListMapper<>(apiUserMapper);
    }

    @Provides
    @Singleton
    public ListMapper<DbUser, User> provideDbListUserMapper(DbUserMapper dbUserMapper) {
        return new ListMapper<>(dbUserMapper);
    }

}
