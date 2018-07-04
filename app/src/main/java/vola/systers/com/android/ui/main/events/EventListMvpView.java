package vola.systers.com.android.ui.main.events;

import android.support.annotation.NonNull;

import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 6/5/18.
 */

public interface EventListMvpView extends MvpView {

    void showEventListSuccessful();

    void showError();

    void showProgressDialog(@NonNull String message);

    void hideProgressDialog();
}
