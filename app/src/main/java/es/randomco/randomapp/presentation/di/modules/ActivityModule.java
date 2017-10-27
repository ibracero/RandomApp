package es.randomco.randomapp.presentation.di.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import es.randomco.randomapp.presentation.di.qualifiers.PerActivity;
import es.randomco.randomapp.presentation.view.dialog.DialogManager;
import es.randomco.randomapp.presentation.view.dialog.DialogManagerImp;

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public DialogManager provideDialogManager(DialogManagerImp dialogManagerImp) {
        return dialogManagerImp;
    }
}
