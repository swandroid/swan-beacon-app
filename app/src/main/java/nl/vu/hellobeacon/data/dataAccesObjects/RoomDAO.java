package nl.vu.hellobeacon.data.dataAccesObjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nl.vu.hellobeacon.data.entities.Room;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RoomDAO {
    @Query("SELECT * FROM room ORDER BY roomName")
    LiveData<List<Room>> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Room room);

    @Delete
    void delete(Room room);

    @Query("DELETE FROM room")
    void deleteAll();

}
