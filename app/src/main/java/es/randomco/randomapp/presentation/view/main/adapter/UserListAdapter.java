package es.randomco.randomapp.presentation.view.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.randomco.randomapp.R;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.view.main.adapter.interfaces.OnUserClickListener;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> mDataset, filterList;
    private OnUserClickListener mOnUserClickListener;

    public UserListAdapter(List<User> dataset) {
        mDataset = dataset;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view, mOnUserClickListener);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = mDataset.get(position);
        holder.render(user);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        mOnUserClickListener = onUserClickListener;
    }
}
