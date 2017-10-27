package es.randomco.randomapp.presentation.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import es.randomco.randomapp.R;
import es.randomco.randomapp.domain.entities.User;
import es.randomco.randomapp.presentation.base.BaseActivity;
import es.randomco.randomapp.utils.CircleTransform;

public class DetailActivity extends BaseActivity {

    public static final String EXTRA_USER = "extra_user";

    @BindView(R.id.image_view_user_profile)
    ImageView mImageViewUserProfile;

    @BindView(R.id.text_view_gender)
    TextView mTextViewGender;

    @BindView(R.id.text_view_name)
    TextView mTextViewName;

    @BindView(R.id.text_view_location)
    TextView mTextViewLocation;

    @BindView(R.id.text_view_registered_date)
    TextView mTextViewRegisteredDate;

    @BindView(R.id.text_view_email)
    TextView mTextViewEmail;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            mUser = getIntent().getParcelableExtra(EXTRA_USER);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();
    }

    private void initUI() {
        Glide.with(this)
                .load(mUser.getProfilePicture().getLarge())
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_person)
                .crossFade()
                .transform(new CircleTransform(this))
                .into(mImageViewUserProfile);

        mTextViewGender.setText(mUser.getGender());
        mTextViewName.setText(mUser.getName().toString());
        mTextViewLocation.setText(mUser.getAddress().toString());
        mTextViewRegisteredDate.setText(mUser.getRegisteredDate());
        mTextViewEmail.setText(mUser.getEmail());
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    public static void open(Activity context, User user) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_USER, user);
        context.startActivity(intent);
    }
}
