package vola.systers.com.android.ui.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vola.systers.com.android.R;
import vola.systers.com.android.ui.base.BaseActivity;
import vola.systers.com.android.ui.main.MainActivity;
import vola.systers.com.android.ui.signup.SignUpActivity;

/**
 * Created by haroon on 24/05/18.
 */

public class SignInActivity extends BaseActivity implements SignInMvpView {

    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;

    private SignInPresenter signInPresenter;
    private Intent intent;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        signInPresenter = new SignInPresenter();
        signInPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        signInPresenter.detachView();
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        String userName = etUserName.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            //TODO: Add more validation
            etUserName.setError(getString(R.string.enter_username));
            return;
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            //TODO: Add more validation
            etPassword.setError(getString(R.string.enter_password));
            return;
        }

        signInPresenter.doSignIn(userName, password);
        showProgressDialog();
    }

    @OnClick(R.id.tv_skip)
    public void skip() {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tv_sign_up)
    public void signUp() {
        intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signInSuccessful() {
        hideProgressDialog();
        Toast.makeText(this, R.string.signin_successful, Toast.LENGTH_SHORT).show();
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signInFailed(@Nullable String errorMessage) {
        hideProgressDialog();
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = getString(R.string.login_unsuccessful);
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
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
