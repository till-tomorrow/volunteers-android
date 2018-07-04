package vola.systers.com.android.ui.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioGroup;

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
    @BindView(R.id.et_first_name) EditText etFirstName;
    @BindView(R.id.et_last_name) EditText etLastName;
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
        String username = etEmail.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etEmail.setError(getString(R.string.enter_username));
            return;
        }

        String firstName = etFirstName.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            etFirstName.setError(getString(R.string.enter_username));
            return;
        }


        String lastName = etLastName.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            etLastName.setError(getString(R.string.enter_name));
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
            Snackbar.make(findViewById(android.R.id.content), R.string.attendence_type,
                    Snackbar.LENGTH_SHORT).show();
            return;
        }

        showProgressDialog();
        registrationPresenter.doRegistration(email, firstName, lastName, affiliations,
                attendanceType);
    }

    @Override
    public void registrationSuccessful() {
        hideProgressDialog();
        Snackbar.make(findViewById(android.R.id.content), R.string.registration_successful,
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void registrationFailed(@Nullable String errorMessage) {
        hideProgressDialog();
        Snackbar.make(findViewById(android.R.id.content), R.string.registration_failed,
                Snackbar.LENGTH_SHORT).show();
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
