package vola.systers.com.android.data.db.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import vola.systers.com.android.data.db.VolunteerDatabase;
import vola.systers.com.android.data.db.daos.EventDao;
import vola.systers.com.android.data.model.Event;

/**
 * Created by haroon on 6/25/18.
 */

public class EventRepository {

    private EventDao eventDao;
    private LiveData<List<Event>> events;
    private LiveData<Event> event;

    public EventRepository(Application application) {
        VolunteerDatabase volunteerDatabase = VolunteerDatabase.getDb(application);
        eventDao = volunteerDatabase.eventDao();
        events = eventDao.getEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return events;
    }

    public LiveData<Event> getEventById(int id) {
        return eventDao.getEventById(id);
    }

    public void insertEvents (Event... events) {
        new insertAsyncEvents(eventDao).execute(events);
    }

    private static class insertAsyncEvents extends AsyncTask<Event, Void, Void> {

        private EventDao asyncEventDao;

        insertAsyncEvents(EventDao eventDao) {
            asyncEventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            asyncEventDao.insertEvents(events);
            return null;
        }
    }
}
