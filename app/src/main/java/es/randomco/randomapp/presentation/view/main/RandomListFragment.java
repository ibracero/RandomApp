package es.randomco.randomapp.presentation.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.randomco.randomapp.R;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.base.BaseFragment;
import es.randomco.randomapp.presentation.view.main.adapter.UserListAdapter;
import es.randomco.randomapp.presentation.view.main.adapter.interfaces.OnUserClickListener;

public class RandomListFragment extends BaseFragment {

    @BindView(R.id.random_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.text_view_empty_list)
    View mEmptyView;

    private List<User> mUserList = new ArrayList<>();
    private UserListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_random_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new UserListAdapter(mUserList);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }


    public void updateContent(List<User> users) {

        mUserList.clear();

        if (users != null && !users.isEmpty()) {
            mUserList.addAll(users);
            mEmptyView.setVisibility(View.GONE);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
        }

        mAdapter.notifyDataSetChanged();
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        if (mAdapter != null) {
            mAdapter.setOnUserClickListener(onUserClickListener);
        }
    }

}
