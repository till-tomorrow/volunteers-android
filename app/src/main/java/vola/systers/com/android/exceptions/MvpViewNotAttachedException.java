package vola.systers.com.android.exceptions;

/**
 * Created by haroon on 6/21/18.
 */

public class MvpViewNotAttachedException extends RuntimeException {

    public MvpViewNotAttachedException() {
        super("Please call Presenter.attachView(MvpView) before" +
                " requesting data to the Presenter");
    }
}
