package vola.systers.com.android.ui.signin;

import android.support.annotation.NonNull;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 24/05/18.
 */

public class SignInPresenter extends BasePresenter<SignInMvpView> {

    @Override
    public void attachView(SignInMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void doSignIn(@NonNull String userName, @NonNull String password) {
        checkViewAttached();
        //TODO: Make the network call here.
        //TODO: Call the getMvpView().signInSuccessful() or getMvpView().signInFailed() implemented in SignInActivity here.
    }
}
