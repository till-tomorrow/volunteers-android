package vola.systers.com.android.ui.signup;

import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 27/05/18.
 */

public interface SignUpMvpView extends MvpView {

    void signUpSuccessful();

    void signUpFailed(@Nullable String errorMessage);

    void showProgressDialog();

    void hideProgressDialog();
}
