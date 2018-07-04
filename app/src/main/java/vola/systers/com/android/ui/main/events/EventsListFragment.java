package vola.systers.com.android.ui.main.events;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vola.systers.com.android.R;
import vola.systers.com.android.model.Event;
import vola.systers.com.android.ui.eventdetails.EventDetailsActivity;

public class EventsListFragment extends Fragment implements EventListMvpView {

    private ProgressDialog progressDialog;
    private EventListPresenter eventListPresenter;
    private ListView eventListView;
    private EventListAdapter eventListAdapter;

    ArrayList<Event> eventList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.eventslist_fragment, container, false);
        eventList = new ArrayList<>();
        eventListView = rootView.findViewById(R.id.list);

        eventListPresenter = new EventListPresenter();
        eventListPresenter.attachView(this);
        eventListPresenter.fetchEvents();

        return rootView;
    }

    @Override
    public void showEventListSuccessful() {
        hideProgressDialog();
        eventListAdapter = new EventListAdapter(eventList, getContext());
        eventListView.setAdapter(eventListAdapter);
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError() {
        hideProgressDialog();
        Toast.makeText(getActivity(), R.string.failed_to_fetch_events, Toast.LENGTH_SHORT).show();
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
