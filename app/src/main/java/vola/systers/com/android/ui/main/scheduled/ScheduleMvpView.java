package vola.systers.com.android.ui.main.scheduled;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 6/5/18.
 */

public interface ScheduleMvpView extends MvpView {

    void showScheduleSuccessful();

    void showError(@Nullable String error);

    void showProgressDialog(@NonNull String message);

    void hideProgressDialog();
}
