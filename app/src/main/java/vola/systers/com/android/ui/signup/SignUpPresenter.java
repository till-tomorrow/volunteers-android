package vola.systers.com.android.ui.signup;

import android.support.annotation.NonNull;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 27/05/18.
 */

public class SignUpPresenter extends BasePresenter<SignUpMvpView> {

    @Override
    public void attachView(SignUpMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void doSignUp(@NonNull String email, @NonNull String userName, @NonNull String name,
                         @NonNull String password, @NonNull String confirmPassword) {
        checkViewAttached();
        //TODO: Make the retrofit call here.
        //TODO: Call the getMvpView().signUpSuccessful() or getMvpView().signUpFailed() implemented in SignUpActivity here.
    }
}
