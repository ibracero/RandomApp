package es.randomco.randomapp.presentation.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.randomco.randomapp.R;
import es.randomco.randomapp.presentation.RandomApp;
import es.randomco.randomapp.presentation.di.components.AppComponent;
import es.randomco.randomapp.presentation.di.modules.ActivityModule;
import es.randomco.randomapp.presentation.view.MvpView;
import es.randomco.randomapp.presentation.view.dialog.DialogManager;
import es.randomco.randomapp.utils.Utils;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    @Inject
    public DialogManager mDialogManager;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }

        initializeToolbar();

        if (Utils.isTablet(this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        }
    }

    public abstract int getLayoutId();

    protected AppComponent getAppComponent() {
        return ((RandomApp) getApplication()).getAppComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void initializeToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public void showLoading() {
        mDialogManager.showLoading();
    }

    @Override
    public void hideLoading() {
        mDialogManager.hideLoading();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.animation_enter_right, R.anim.animation_leave_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_enter_left, R.anim.animation_leave_left);
    }
}
