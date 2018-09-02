package nl.vu.hellobeacon.data.dataAccesObjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;


@Dao
public interface BeaconDistanceMeasurementDAO {
    @Insert
    void insert(BeaconDistanceMeasurement beaconDistanceMeasurement);

    @Query("SELECT * FROM BeaconDistanceMeasurement WHERE `index` = :index")
    LiveData<BeaconDistanceMeasurement> getDistanceMeasurement(int index);

    @Query("SELECT MAX(`index`) FROM beaconDistanceMeasurement")
    LiveData<Integer> getMaxIndex();

}
