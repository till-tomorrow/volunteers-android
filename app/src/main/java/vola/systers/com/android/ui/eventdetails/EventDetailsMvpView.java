package vola.systers.com.android.ui.eventdetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import vola.systers.com.android.model.Event;
import vola.systers.com.android.ui.base.MvpView;

/**
 * Created by haroon on 28/05/18.
 */

public interface EventDetailsMvpView extends MvpView {

    void eventFetchSuccessful(@NonNull Event event);

    void eventFetchFailed(@Nullable String errorMessage);

    void showProgressDialog();

    void hideProgressDialog();
}
