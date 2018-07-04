package vola.systers.com.android.ui.eventdetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import vola.systers.com.android.data.db.repositories.EventRepository;
import vola.systers.com.android.data.model.Event;

/**
 * Created by haroon on 6/25/18.
 */

public class EventDetailViewModel extends AndroidViewModel {

    private EventRepository eventRepository;
    private LiveData<List<Event>> events;

    public EventDetailViewModel(@NonNull Application application) {
        super(application);

        eventRepository = new EventRepository(application);
        events = eventRepository.getAllEvents();

    }

    public LiveData<List<Event>> getEvents(int id) {
        return eventRepository.getAllEvents();
    }

    public LiveData<Event> getEventById(int id) {
        return eventRepository.getEventById(id);
    }
}
