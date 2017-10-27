package es.randomco.randomapp.presentation;

import android.app.Application;

import es.randomco.randomapp.presentation.di.components.AppComponent;
import es.randomco.randomapp.presentation.di.components.DaggerAppComponent;
import es.randomco.randomapp.presentation.di.modules.AppModule;

public class RandomApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
    }

    private void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
