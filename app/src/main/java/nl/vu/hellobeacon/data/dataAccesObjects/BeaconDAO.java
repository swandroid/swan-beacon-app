package nl.vu.hellobeacon.data.dataAccesObjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nl.vu.hellobeacon.data.entities.Beacon;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BeaconDAO {
    @Query("SELECT * FROM beacon")
    LiveData<List<Beacon>> getAll();

    @Query("SELECT COUNT(*) FROM BEACON")
    LiveData<Integer> count();

    @Insert(onConflict = REPLACE)
    void insert(Beacon beacon);

    @Delete
    void delete(Beacon beacon);
}
