package vola.systers.com.android.ui.profile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 28/05/18.
 */

public interface ProfileMvpView extends MvpView {

    void profileFetchSuccessful(@NonNull String email, @NonNull String username,
                                @NonNull String name, @NonNull String title,
                                @NonNull String affiliation);

    void profileFetchFailed(@Nullable String errorMessage);

    void profileUpdateSuccessful(@NonNull String username, @NonNull String name,
                                 @NonNull String title, @NonNull String affiliation);

    void profileUpdateFailed(@Nullable String errorMessage);

    void logoutSuccessful();

    void logoutFailed(@Nullable String errorMessage);

    void showProgressDialog(@NonNull String message);

    void hideProgressDialog();
}
