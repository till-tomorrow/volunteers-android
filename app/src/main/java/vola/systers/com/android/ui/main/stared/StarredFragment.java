package vola.systers.com.android.ui.main.stared;

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
import vola.systers.com.android.model.Event;
import vola.systers.com.android.ui.eventdetails.EventDetailsActivity;

public class StarredFragment extends Fragment implements StaredMvpView {

    private ProgressDialog progressDialog;
    private StaredPresenter staredPresenter;
    private ListView eventsListView;
    private StarredListAdapter eventListAdapter;
    private ArrayList<Event> eventList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.starred_list_fragment, container, false);
        eventList = new ArrayList<>();
        eventsListView = rootView.findViewById(R.id.list);

        staredPresenter = new StaredPresenter();
        staredPresenter.attachView(this);
        staredPresenter.fetchStaredEvents();

        return rootView;
    }

    @Override
    public void showStaredEventsSuccessful() {
        hideProgressDialog();
        //TODO: Update event list
        eventListAdapter = new StarredListAdapter(eventList,getContext());
        eventsListView.setAdapter(eventListAdapter);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError(@Nullable String error) {
        hideProgressDialog();
        if (TextUtils.isEmpty(error)) {
            error = "Failed to fetch stared events";
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