package es.randomco.randomapp.presentation.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import es.randomco.randomapp.presentation.RandomApp;
import es.randomco.randomapp.presentation.di.components.AppComponent;
import es.randomco.randomapp.presentation.di.modules.ActivityModule;

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = null;

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            rootView = inflater.inflate(layoutId, container, false);
            ButterKnife.bind(this, rootView);
        }

        return rootView;
    }

    public abstract int getLayoutId();

    protected AppComponent getAppComponent() {
        return ((RandomApp) getActivity().getApplication()).getAppComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }
}
