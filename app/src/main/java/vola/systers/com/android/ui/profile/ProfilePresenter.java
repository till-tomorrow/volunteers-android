package vola.systers.com.android.ui.profile;

import android.support.annotation.NonNull;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 28/05/18.
 */

public class ProfilePresenter extends BasePresenter<ProfileMvpView> {

    @Override
    public void attachView(ProfileMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void fetchProfile() {
        checkViewAttached();
        //TODO: Make the retrofit request here
        //TODO: Call the getMvpView().profileFetchSuccessful() or getMvpView().profileFetchFailed() implemented in ProfileActivity here.
    }

    public void updateProfile(@NonNull String username, @NonNull String Name,
                                  @NonNull String title, @NonNull String affiliation) {
        checkViewAttached();
        //TODO: Make the retrofit request here
        //TODO: Call the getMvpView().profileUpdateSuccessful() or getMvpView().profileUpdateFailed() implemented in ProfileActivity here.
    }
}
