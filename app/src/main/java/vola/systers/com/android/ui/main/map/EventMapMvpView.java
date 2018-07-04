package vola.systers.com.android.ui.main.map;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 6/5/18.
 */

public interface EventMapMvpView extends MvpView {

    void showMapSuccessful(@NonNull int eventId);

    void showError(@Nullable String error);

    void showProgressDialog(@NonNull String message);

    void hideProgressDialog();
}
