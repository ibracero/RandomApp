package es.randomco.randomapp.presentation.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import es.randomco.randomapp.domain.executor.InteractorExecutor;
import es.randomco.randomapp.domain.executor.MainThreadExecutor;
import es.randomco.randomapp.domain.repository.datasource.DbDataSource;
import es.randomco.randomapp.domain.repository.datasource.NetworkDataSource;
import es.randomco.randomapp.presentation.di.modules.ApiModule;
import es.randomco.randomapp.presentation.di.modules.AppModule;
import es.randomco.randomapp.presentation.di.modules.DataModule;
import es.randomco.randomapp.presentation.di.modules.DbModule;
import es.randomco.randomapp.presentation.di.modules.ExecutorModule;

@Singleton
@Component(modules = {AppModule.class, ExecutorModule.class, ApiModule.class, DbModule.class, DataModule.class})
public interface AppComponent {

    Application app();

    InteractorExecutor interactorExecutor();

    MainThreadExecutor mainThreadExecutor();

    NetworkDataSource networkDataSource();

    DbDataSource dbDataSource();

}
