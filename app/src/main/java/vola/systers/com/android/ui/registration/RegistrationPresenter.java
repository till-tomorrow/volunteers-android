package vola.systers.com.android.ui.registration;

import android.support.annotation.NonNull;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 28/05/18.
 */

public class RegistrationPresenter extends BasePresenter<RegistrationMvpView> {

    @Override
    public void attachView(RegistrationMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void doRegistration(@NonNull String email, @NonNull String username,
                               @NonNull String name, @NonNull String affiliation,
                               @NonNull String attendanceType) {
        checkViewAttached();
        //TODO: Make the network call here.
        //TODO: Call the getMvpView().registrationSuccessful() or getMvpView().registrationUnsuccessful() implemented in RegistrationActivity here.
    }
}
