package es.randomco.randomapp.presentation.view.main.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import es.randomco.randomapp.R;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.base.BaseActivity;
import es.randomco.randomapp.presentation.di.components.DaggerActivityComponent;
import es.randomco.randomapp.presentation.presenter.MainPresenter;
import es.randomco.randomapp.presentation.view.detail.DetailActivity;
import es.randomco.randomapp.presentation.view.main.MainMvpView;
import es.randomco.randomapp.presentation.view.main.RandomListFragment;
import es.randomco.randomapp.presentation.view.main.adapter.interfaces.OnUserClickListener;
import es.randomco.randomapp.presentation.view.main.adapter.interfaces.QueryTextListenerImp;
import es.randomco.randomapp.presentation.view.nearby.NearbyActivity;

public class MainActivity extends BaseActivity implements MainMvpView, OnUserClickListener, OnFilterListener {

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 101;

    @Inject
    MainPresenter mPresenter;

    RandomListFragment mRandomListFragment;
    RandomListFragment mFavoritesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInjector();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
        initFragments();

        mPresenter.attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new QueryTextListenerImp(this));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_location:
                if (mPresenter.getCurrentUserList() != null && !mPresenter.getCurrentUserList().isEmpty()) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                    } else {
                        goToNearByActivity();
                    }
                } else {
                    mDialogManager.showInfoDialog(R.string.dialog_alert_title, R.string.message_empty_random_list, null);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void generateRandomUsers() {
        mPresenter.generateRandomUsers();
    }

    private void initFragments() {
        mRandomListFragment = (RandomListFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_tag_random_list));
        mFavoritesListFragment = (RandomListFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_tag_favorite_list));
    }

    @Override
    public void showUsers(List<User> users) {
        if (mRandomListFragment != null && mRandomListFragment.isAdded()) {
            mRandomListFragment.setOnUserClickListener(this);
            mRandomListFragment.updateContent(users);
        }

        if (mFavoritesListFragment != null && mFavoritesListFragment.isAdded()) {
            mFavoritesListFragment.setOnUserClickListener(this);
            mFavoritesListFragment.updateContent(filterFavorites(users));
        }
    }

    private List<User> filterFavorites(List<User> all) {
        List<User> favorites = new ArrayList<>();

        for (User user : all) {
            if (user.isFavorite()) favorites.add(user);
        }

        return favorites;
    }

    @Override
    public void showGetUsersError() {
        mDialogManager.showInfoDialog(R.string.dialog_alert_title, R.string.generic_api_error, null);
    }

    @Override
    public void goToDetailActivity(User user) {
        DetailActivity.open(this, user);
    }

    @Override
    public void showDeleteUserError() {
        mDialogManager.showInfoDialog(R.string.dialog_alert_title, R.string.generic_action_error, null);
    }

    private void goToNearByActivity() {
        NearbyActivity.open(this, (ArrayList<User>) mPresenter.getCurrentUserList());
    }

    @Override
    public void onUserClick(User user) {
        mPresenter.onUserClick(user);
    }

    @Override
    public void onUserChangeFavorite(User user) {
        mPresenter.onUserChanged(user);
    }

    @Override
    public void onDeleteUser(User user) {
        mPresenter.deleteUser(user);
    }

    @Override
    public void filter(String string) {
        mPresenter.filter(string);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goToNearByActivity();
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }
}
