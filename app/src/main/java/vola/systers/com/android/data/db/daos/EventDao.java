package vola.systers.com.android.data.db.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import vola.systers.com.android.data.model.Event;


/**
 * Created by haroon on 6/25/18.
 */

@Dao
public interface EventDao {

    @Query("SELECT * FROM Event")
    LiveData<List<Event>> getEvents();

    @Query("SELECT * FROM Event where id=:id")
    LiveData<Event> getEventById(int id);

    @Insert
    void insertEvents(Event... events);
}
