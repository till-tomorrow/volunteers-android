package vola.systers.com.android.ui.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vola.systers.com.android.R;
import vola.systers.com.android.ui.base.BaseActivity;

/**
 * Created by haroon on 28/05/18.
 */

public class RegistrationActivity extends BaseActivity implements RegistrationMvpView {

    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_username) EditText etUsername;
    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_affiliations) EditText etAffiliations;
    @BindView(R.id.rad_attendees) RadioGroup radAttendees;

    private RegistrationPresenter registrationPresenter;
    private Intent intent;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        registrationPresenter = new RegistrationPresenter();
        registrationPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        registrationPresenter.detachView();
    }

    @OnClick(R.id.btn_register)
    public void register() {
        //TODO: Add more validation
        String email = etEmail.getText().toString();
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

        String affiliations = etAffiliations.getText().toString();
        if (TextUtils.isEmpty(affiliations)) {
            etAffiliations.setError(getString(R.string.enter_affiliations));
            return;
        }

        String attendanceType;
        int checkedButton = radAttendees.getCheckedRadioButtonId();
        if (checkedButton == R.id.rad_student) {
            attendanceType = "Student";
        } else if (checkedButton == R.id.rad_volunteer) {
            attendanceType = "Volunteer";
        } else if (checkedButton == R.id.rad_others) {
            attendanceType = "Others";
        } else {
            Toast.makeText(this, R.string.attendence_type, Toast.LENGTH_SHORT).show();
            return;
        }

        showProgressDialog();
        registrationPresenter.doRegistration(email, username, name, affiliations, attendanceType);
    }

    @Override
    public void registrationSuccessful() {
        hideProgressDialog();
        Toast.makeText(this, R.string.registration_successful, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registrationFailed(@Nullable String errorMessage) {
        hideProgressDialog();
        Toast.makeText(this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            //TODO: Set this to false
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(getString(R.string.signing_in));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
