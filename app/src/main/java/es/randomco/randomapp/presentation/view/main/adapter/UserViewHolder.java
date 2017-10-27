package es.randomco.randomapp.presentation.view.main.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.randomco.randomapp.R;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.view.main.adapter.interfaces.OnUserClickListener;
import es.randomco.randomapp.utils.CircleTransform;

public class UserViewHolder extends RecyclerView.ViewHolder {

    OnUserClickListener mUserListener;

    @BindView(R.id.button_favorite)
    ImageView mFavorite;

    @BindView(R.id.text_view_name)
    TextView mName;

    @BindView(R.id.text_view_phone)
    TextView mPhone;

    @BindView(R.id.text_view_email)
    TextView mEmail;

    @BindView(R.id.image_view_user_profile)
    ImageView mProfilePicture;

    @BindView(R.id.button_delete)
    ImageView mDelete;

    @BindView(R.id.text_view_distance)
    TextView mDistance;

    @BindView(R.id.user_info_layout)
    RelativeLayout mContainer;

    UserViewHolder(View view, OnUserClickListener mOnUserClickListener) {
        super(view);
        ButterKnife.bind(this, itemView);

        mUserListener = mOnUserClickListener;
    }

    void render(final User user) {

        if (user.getName() != null) {
            mName.setText(user.getName().toString());
        }

        if (user.getEmail() != null) {
            mEmail.setText(user.getEmail());
        }

        if (user.getPhone() != null) {
            mPhone.setText(user.getPhone());
        }

        drawFavorite(user.isFavorite());

        //If there is no listener attached, hide the affected views
        if (mUserListener != null) {
            mFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.setFavorite(!user.isFavorite());
                    drawFavorite(user.isFavorite());
                    mUserListener.onUserChangeFavorite(user);
                }
            });


            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mUserListener.onDeleteUser(user);
                }
            });

            mContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mUserListener.onUserClick(user);
                }
            });

        } else {
            mFavorite.setVisibility(View.GONE);
            mDelete.setVisibility(View.GONE);

            if (user.getCurrentDistance() != 0.0d) {

                double rounded = Math.round(user.getCurrentDistance() * 100.0) / 100.0;

                mDistance.setText(String.valueOf(rounded) + mDistance.getContext().getString(R.string.distance_unit_km));
                mDistance.setVisibility(View.VISIBLE);
            }
        }


        if (user.getProfilePicture() != null) {
            Glide.with(mProfilePicture.getContext())
                    .load(user.getProfilePicture().getThumbnail())
                    .centerCrop()
                    .crossFade()
                    .transform(new CircleTransform(mProfilePicture.getContext()))
                    .into(mProfilePicture);
        }
    }

    private void drawFavorite(boolean favorite) {
        if (favorite) {
            mFavorite.setBackgroundResource(R.color.colorFavoriteUserBackground);
            mFavorite.setImageDrawable(ContextCompat.getDrawable(mFavorite.getContext(), R.drawable.ic_favorite_selected));
        } else {
            mFavorite.setBackgroundResource(android.R.color.transparent);
            mFavorite.setImageDrawable(ContextCompat.getDrawable(mFavorite.getContext(), R.drawable.ic_favorite_not_selected));
        }
    }
}