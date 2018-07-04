package vola.systers.com.android.ui.main.events;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import vola.systers.com.android.data.model.Event;


/**
 * Created by haroon on 6/25/18.
 */

public class EventViewModel extends AndroidViewModel {

    private LiveData<List<Event>> events;

    public EventViewModel(@NonNull Application application) {
        super(application);

        EventViewModel eventRepository = new EventViewModel(application);
        events = eventRepository.getEvents();
    }

    public LiveData<List<Event>> getEvents() {
        return events;
    }
}
