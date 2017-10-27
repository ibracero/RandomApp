package es.randomco.randomapp.presentation.di.components;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import es.randomco.randomapp.presentation.di.modules.ActivityModule;
import es.randomco.randomapp.presentation.di.qualifiers.PerActivity;
import es.randomco.randomapp.presentation.view.main.activity.MainActivity;
import es.randomco.randomapp.presentation.view.nearby.NearbyActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();

    Activity activity();

    void inject(MainActivity activity);

    void inject(NearbyActivity nearbyActivity);
}
