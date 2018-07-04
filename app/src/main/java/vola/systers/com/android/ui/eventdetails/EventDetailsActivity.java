package vola.systers.com.android.ui.eventdetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vola.systers.com.android.R;
import vola.systers.com.android.data.model.Event;
import vola.systers.com.android.ui.base.BaseActivity;
import vola.systers.com.android.ui.registration.RegistrationActivity;

/**
 * Created by haroon on 28/05/18.
 */

public class EventDetailsActivity extends BaseActivity implements EventDetailsMvpView {

    @BindView(R.id.tv_event_name) TextView etEventName;
    @BindView(R.id.tv_event_description) TextView etEventDescription;
    @BindView(R.id.tv_event_date) TextView etEventDate;
    @BindView(R.id.tv_event_time) TextView etEventTime;
    @BindView(R.id.tv_event_time_zone) TextView etEventTimeZone;
    @BindView(R.id.tv_event_location_name) TextView etEventLocationName;
    @BindView(R.id.tv_event_location_city) TextView etEventLocationCity;
    @BindView(R.id.tv_event_location_state) TextView etEventLocationState;

    private EventDetailsPresenter eventDetailsPresenter;
    private ProgressDialog progressDialog;
    private int eventId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);

        eventDetailsPresenter = new EventDetailsPresenter();
        eventDetailsPresenter.attachView(this);
        eventDetailsPresenter.fetchDetails(eventId);
        showProgressDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        eventDetailsPresenter.detachView();
    }

    @OnClick(R.id.btn_register)
    public void registerForEvent() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        intent.putExtra("event_id", eventId);
        startActivity(intent);
    }

    @OnClick(R.id.btn_bookmark)
    public void bookmarkEvent() {
        //TODO: Bookmark the event here
        Toast.makeText(this, R.string.event_bookmarked, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eventFetchSuccessful(@NonNull Event event) {
        //TODO: Update rest of the views here
        hideProgressDialog();
        etEventName.setText(event.getName());
    }

    @Override
    public void eventFetchFailed(@Nullable String errorMessage) {
        hideProgressDialog();
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = getString(R.string.signup_failed);
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            //TODO: Set this to false
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(getString(R.string.fetch_event_details));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
