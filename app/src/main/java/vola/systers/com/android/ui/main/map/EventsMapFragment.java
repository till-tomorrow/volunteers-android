package vola.systers.com.android.ui.main.map;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import vola.systers.com.android.R;

public class EventsMapFragment extends Fragment implements EventMapMvpView {

    private ProgressDialog progressDialog;
    private EventMapPresenter eventMapPresenter;

    private int eventId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.eventsmap_fragment, container, false);

        eventMapPresenter = new EventMapPresenter();
        eventMapPresenter.attachView(this);
        eventMapPresenter.fetchLocation(eventId);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showMapSuccessful(@NonNull int eventId) {
        //TODO: Set the map view here
        hideProgressDialog();
    }

    @Override
    public void showError(@Nullable String error) {
        hideProgressDialog();
        if (TextUtils.isEmpty(error)) {
            error = "Failed to get location";
        }
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog(@NonNull String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
            //TODO: Set this to false
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
