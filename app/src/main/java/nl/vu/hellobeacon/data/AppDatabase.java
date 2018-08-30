package nl.vu.hellobeacon.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import nl.vu.hellobeacon.data.dataAccesObjects.RoomDAO;

@Database(entities = {nl.vu.hellobeacon.data.entities.Room.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RoomDAO roomDAO();

    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
