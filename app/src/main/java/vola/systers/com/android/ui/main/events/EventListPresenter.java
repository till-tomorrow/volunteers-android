package vola.systers.com.android.ui.main.events;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 6/5/18.
 */

public class EventListPresenter extends BasePresenter<EventListMvpView> {

    @Override
    public void attachView(EventListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void fetchEvents() {
        getMvpView().showProgressDialog("Fetching events");
        checkViewAttached();
        //TODO: Make the retrofit request here
        //TODO: Call the getMvpView().showEventListSuccessful() and getMvpView().showError() methods implemented in EventsListFragment

    }
}
