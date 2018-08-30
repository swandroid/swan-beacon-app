package nl.vu.hellobeacon.data.dataAccesObjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nl.vu.hellobeacon.data.entities.Room;

@Dao
public interface RoomDAO {
    @Query("SELECT * FROM room")
    LiveData<List<Room>> getAll();

    @Insert
    void insert(Room room);

    @Delete
    void delete(Room room);

}
