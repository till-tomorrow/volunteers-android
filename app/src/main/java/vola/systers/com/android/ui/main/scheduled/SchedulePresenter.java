package vola.systers.com.android.ui.main.scheduled;

import vola.systers.com.android.ui.base.BasePresenter;

/**
 * Created by haroon on 6/5/18.
 */

public class SchedulePresenter extends BasePresenter<ScheduleMvpView> {

    @Override
    public void attachView(ScheduleMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void fetchSchedule() {
        getMvpView().showProgressDialog("Fetching schedule");
        checkViewAttached();
        //TODO: Make the retrofit request here
        //TODO: Call getMvpView.showScheduleSuccessful() or getMvpView.showError() implemented in ScheduleFragment
    }
}
