package vola.systers.com.android.ui.main.stared;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 6/5/18.
 */

public interface StaredMvpView extends MvpView {

    void showStaredEventsSuccessful();

    void showError(@Nullable String error);

    void showProgressDialog(@NonNull String message);

    void hideProgressDialog();
}
