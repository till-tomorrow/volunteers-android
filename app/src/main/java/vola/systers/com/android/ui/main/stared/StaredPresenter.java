package vola.systers.com.android.ui.main.stared;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 6/5/18.
 */

public class StaredPresenter extends BasePresenter<StaredMvpView> {

    @Override
    public void attachView(StaredMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void fetchStaredEvents() {
        getMvpView().showProgressDialog("Fetching stared events");
        checkViewAttached();
        //TODO: Make the retrofit request here
        //TODO: Call getMvpView.showStaredEventsSuccessful() or getMvpView.showError() implemented in StarredFragment
    }
}
