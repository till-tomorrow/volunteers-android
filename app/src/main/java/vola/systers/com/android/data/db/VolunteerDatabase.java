package vola.systers.com.android.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import vola.systers.com.android.data.db.daos.EventDao;
import vola.systers.com.android.data.model.Event;

/**
 * Created by haroon on 6/25/18.
 */

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class VolunteerDatabase extends RoomDatabase {

    private static VolunteerDatabase volunteerDatabase;

    public abstract EventDao eventDao();

    public static VolunteerDatabase getDb(final Context context) {
        if (volunteerDatabase == null) {
            synchronized (VolunteerDatabase.class) {
                if (volunteerDatabase == null) {
                    volunteerDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            VolunteerDatabase.class, "vola_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return volunteerDatabase;
    }
}
