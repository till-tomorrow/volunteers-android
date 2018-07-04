package vola.systers.com.android.ui.eventdetails;

import android.support.annotation.NonNull;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 28/05/18.
 */

public class EventDetailsPresenter extends BasePresenter<EventDetailsMvpView> {

    @Override
    public void attachView(EventDetailsMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void fetchDetails(@NonNull int id) {
        checkViewAttached();
        //TODO: Make the retrofit call here.
        //TODO: Call the getMvpView().eventFetchSuccessful() or getMvpView().eventFetchFailed() implemented in EventDetailsActivity here.
    }
}
