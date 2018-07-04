package vola.systers.com.android.ui.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vola.systers.com.android.R;
import vola.systers.com.android.ui.base.BaseActivity;
import vola.systers.com.android.ui.main.MainActivity;
import vola.systers.com.android.ui.signin.SignInActivity;

/**
 * Created by haroon on 27/05/18.
 */

public class SignUpActivity extends BaseActivity implements SignUpMvpView {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_confirm)
    EditText etPasswordConfirm;

    private SignUpPresenter signUpPresenter;
    private Intent intent;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUpPresenter = new SignUpPresenter();
        signUpPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        signUpPresenter.detachView();
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp() {
        //TODO: Add more validations
        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.enter_email));
            return;
        }

        String userName = etUserName.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            etUserName.setError(getString(R.string.enter_username));
            return;
        }

        String name = etName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            etName.setError(getString(R.string.enter_name));
            return;
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError(getString(R.string.enter_password));
            return;
        }

        String confirmPassword = etPasswordConfirm.getText().toString();
        if (TextUtils.isEmpty(confirmPassword)) {
            etPasswordConfirm.setError(getString(R.string.confirm_password));
            return;
        }

        if (!password.equals(confirmPassword)) {
            Snackbar.make(findViewById(android.R.id.content), R.string.passwords_mismatch,
                    Snackbar.LENGTH_SHORT).show();
            return;
        }

        signUpPresenter.doSignUp(email, userName, name, password, confirmPassword);
        showProgressDialog();
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_skip)
    public void skip() {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signUpSuccessful() {
        hideProgressDialog();
        Snackbar.make(findViewById(android.R.id.content), R.string.signup_successful,
                Snackbar.LENGTH_SHORT).show();
        intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signUpFailed(@Nullable String errorMessage) {
        hideProgressDialog();
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = getString(R.string.signup_failed);
        }
        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            //TODO: Set this to false
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(getString(R.string.signing_up));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
