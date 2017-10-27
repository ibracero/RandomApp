package es.randomco.randomapp.presentation.view.nearby;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.randomco.randomapp.R;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.base.BaseActivity;
import es.randomco.randomapp.presentation.di.components.DaggerActivityComponent;
import es.randomco.randomapp.presentation.presenter.NearbyPresenter;
import es.randomco.randomapp.presentation.view.main.RandomListFragment;

public class NearbyActivity extends BaseActivity implements NearbyMvpView {

    private static final String EXTRA_USER_LIST = "extra_user_list";

    List<User> mNearbyUserList;

    RandomListFragment mRandomListFragment;

    @Inject
    NearbyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInjector();

        if (getIntent().getExtras() != null) {
            mNearbyUserList = getIntent().getParcelableArrayListExtra(EXTRA_USER_LIST);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeInjector() {
        DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragment();
        mPresenter.attachView(this);

        filterByPosition();
    }

    private void initFragment() {
        mRandomListFragment = (RandomListFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_tag_nearby_list));
    }

    private void filterByPosition() {
        Location currentLocation = mPresenter.getCurrentLocation(this);

        if (currentLocation != null) {
            mPresenter.filterByDistance(mNearbyUserList, currentLocation.getLatitude(), currentLocation.getLongitude());
        } else {
            mDialogManager.showInfoDialog(R.string.dialog_alert_title, R.string.gps_error, null);
        }
    }

    @Override
    public void showNearbyUsers(List<User> response) {
        mRandomListFragment.updateContent(response);
    }

    @Override
    public void showError() {
        mDialogManager.showInfoDialog(R.string.dialog_alert_title, R.string.generic_action_error, null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_nearby;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void open(Activity context, ArrayList<User> nearbyUsers) {
        Intent intent = new Intent(context, NearbyActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_USER_LIST, nearbyUsers);
        context.startActivity(intent);
    }


}
