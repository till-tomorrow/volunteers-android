package vola.systers.com.android.ui.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import vola.systers.com.android.R;
import vola.systers.com.android.ui.base.BaseActivity;

/**
 * Created by haroon on 28/05/18.
 */

public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    @BindView(R.id.img_profile)
    ImageView imgProfile;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_affiliations)
    EditText etAffiliation;
    @BindView(R.id.btn_update_profile)
    Button btUpdateProfile;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ProfilePresenter profilePresenter;
    private ProgressDialog progressDialog;
    private String currentEmail;
    private String currentUsername;
    private String currentName;
    private String currentTitle;
    private String currentAffiliation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        toolbar.setTitle(getString(R.string.profile));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        profilePresenter = new ProfilePresenter();
        profilePresenter.attachView(this);
        profilePresenter.fetchProfile();
        showProgressDialog(getString(R.string.fetching_your_profile));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        profilePresenter.detachView();
    }

    @OnClick(R.id.btn_update_profile)
    public void updateProfile() {
        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.enter_email));
            return;
        }
        String username = etUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etUsername.setError(getString(R.string.enter_username));
            return;
        }

        String name = etName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            etName.setError(getString(R.string.enter_name));
            return;
        }

        String title = etTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            etTitle.setError(getString(R.string.enter_title));
            return;
        }

        String affiliation = etAffiliation.getText().toString();
        if (TextUtils.isEmpty(affiliation)) {
            etAffiliation.setError(getString(R.string.enter_affiliation));
            return;
        }
        profilePresenter.updateProfile(username, name, title, affiliation);
        showProgressDialog(getString(R.string.updating_your_profile));
    }

    @Override
    public void showProgressDialog(@NonNull String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            //TODO: Set this to false
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void profileFetchSuccessful(@NonNull String profileUrl, @NonNull String email,
                                       @NonNull String username, @NonNull String name,
                                       @NonNull String title, @NonNull String affiliation) {
        hideProgressDialog();
        Picasso.get()
                .load(profileUrl)
                .resize(150, 150)
                .centerCrop()
                .into(imgProfile);
        etEmail.setText(email);
        etUsername.setText(username);
        etName.setText(name);
        etTitle.setText(title);
        etAffiliation.setText(affiliation);
        currentEmail = email;
        currentUsername = username;
        currentName = name;
        currentTitle = title;
        currentAffiliation = affiliation;
    }

    @Override
    public void profileFetchFailed(@Nullable String errorMessage) {
        hideProgressDialog();
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = getString(R.string.failed_fetch_profile);
        }
        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void profileUpdateSuccessful(@NonNull String username, @NonNull String name,
                                        @NonNull String title, @NonNull String affiliation) {
        currentUsername = username;
        currentName = name;
        currentTitle = title;
        currentAffiliation = affiliation;
        //TODO: Updated the view and show the new profile details
        etUsername.setText(username);
        etName.setText(name);
        etTitle.setText(title);
        etAffiliation.setText(affiliation);
    }

    @Override
    public void profileUpdateFailed(@Nullable String errorMessage) {
        etUsername.setText(currentEmail);
        etName.setText(currentUsername);
        etTitle.setText(currentTitle);
        etAffiliation.setText(currentAffiliation);
        hideProgressDialog();
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = getString(R.string.failed_update_profile);
        }
        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT)
                .show();
    }

    @OnTextChanged(value = {R.id.et_username, R.id.et_name, R.id.et_title, R.id.et_affiliations},
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void dataChanged() {
        if (!etUsername.getText().toString().equals(currentUsername) ||
                !etName.getText().toString().equals(currentName) ||
                !etTitle.getText().toString().equals(currentTitle) ||
                !etAffiliation.getText().toString().equals(currentAffiliation)) {
            btUpdateProfile.setVisibility(View.VISIBLE);
        } else {
            btUpdateProfile.setVisibility(View.INVISIBLE);
        }
    }
}
