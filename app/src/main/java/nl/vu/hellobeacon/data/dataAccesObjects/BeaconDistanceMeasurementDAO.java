package nl.vu.hellobeacon.data.dataAccesObjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface BeaconDistanceMeasurementDAO {
    @Insert(onConflict = REPLACE)
    void insert(BeaconDistanceMeasurement beaconDistanceMeasurement);

    @Query("SELECT * FROM BeaconDistanceMeasurement WHERE `index` = :index")
    LiveData<BeaconDistanceMeasurement> getDistanceMeasurement(int index);

    @Query("SELECT MAX(`index`) FROM beaconDistanceMeasurement")
    LiveData<Integer> getMaxIndex();

}
