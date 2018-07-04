package vola.systers.com.android.ui.base;

import vola.systers.com.android.exceptions.MvpViewNotAttachedException;

/**
 * Created by haroon on 24/05/18.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() throws  MvpViewNotAttachedException{
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }
}
