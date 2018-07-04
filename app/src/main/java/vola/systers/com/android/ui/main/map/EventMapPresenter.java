package vola.systers.com.android.ui.main.map;

import android.support.annotation.Nullable;

import vola.systers.com.android.ui.base.BasePresenter;


/**
 * Created by haroon on 6/5/18.
 */

public class EventMapPresenter extends BasePresenter<EventMapMvpView> {

    @Override
    public void attachView(EventMapMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void fetchLocation(@Nullable int eventId) {
        checkViewAttached();
        getMvpView().showProgressDialog("Fetching location");
        //TODO: Make the retrofit request here
        //TODO: Call the getMvpView().showMapSuccessful() or getMvpView().showError() implemented in EventMapFragment here.
    }
}
