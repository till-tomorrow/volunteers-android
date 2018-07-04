package vola.systers.com.android.ui.base;

/**
 * Created by haroon on 24/05/18.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
