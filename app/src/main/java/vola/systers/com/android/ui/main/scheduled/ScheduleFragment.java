package vola.systers.com.android.ui.main.scheduled;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vola.systers.com.android.R;
import vola.systers.com.android.data.model.Event;
import vola.systers.com.android.ui.eventdetails.EventDetailsActivity;

public class ScheduleFragment extends Fragment implements ScheduleMvpView {

    private SchedulePresenter schedulePresenter;
    private ProgressDialog progressDialog;
    private ListView eventsListView;
    private ScheduleListAdapter eventListAdapter;
    private ArrayList<Event> eventList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.schedule_list_fragment, container, false);
        eventList = new ArrayList<>();
        eventsListView = rootView.findViewById(R.id.list);

        schedulePresenter = new SchedulePresenter();
        schedulePresenter.attachView(this);
        schedulePresenter.fetchSchedule();

        return rootView;
    }

    @Override
    public void showScheduleSuccessful() {
        hideProgressDialog();
        //TODO: Update eventList
        eventListAdapter = new ScheduleListAdapter(eventList,getContext());
        eventsListView.setAdapter(eventListAdapter);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event selectedEvent = eventList.get(position);
                Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
                intent.putExtra("selectedEvent", selectedEvent);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError(@Nullable String error) {
        hideProgressDialog();
        if (TextUtils.isEmpty(error)) {
            error = "Failed to fetch schedule";
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