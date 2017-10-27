package es.randomco.randomapp.presentation.view.main.adapter.interfaces;


import android.os.Handler;
import android.support.v7.widget.SearchView;

import es.randomco.randomapp.presentation.view.main.activity.OnFilterListener;

public class QueryTextListenerImp implements SearchView.OnQueryTextListener {

    private Handler handler;
    private OnFilterListener mListener;

    public QueryTextListenerImp(OnFilterListener onFilterListener) {
        mListener = onFilterListener;
        handler = new Handler();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        handler.postDelayed(new Runnable() {
            public void run() {
                mListener.filter(newText);
            }
        }, 750);

        return false;
    }
}
