package vola.systers.com.android.ui.signin;

import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 24/05/18.
 */

public interface SignInMvpView extends MvpView {

    void signInSuccessful();

    void signInFailed(@Nullable String errorMessage);

    void showProgressDialog();

    void hideProgressDialog();
}
