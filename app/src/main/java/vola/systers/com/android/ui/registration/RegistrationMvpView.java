package vola.systers.com.android.ui.registration;

import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 28/05/18.
 */

public interface RegistrationMvpView extends MvpView {

    void registrationSuccessful();

    void registrationFailed(@Nullable String errorMessage);

    void showProgressDialog();

    void hideProgressDialog();
}
