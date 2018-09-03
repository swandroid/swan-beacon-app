package nl.vu.hellobeacon.data.dataAccesObjects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface BeaconDistanceMeasurementDAO {
    @Insert(onConflict = REPLACE)
    void insert(BeaconDistanceMeasurement beaconDistanceMeasurement);

    @Query("SELECT * FROM BeaconDistanceMeasurement")
    List<BeaconDistanceMeasurement> getDistanceMeasurement();

    @Query("SELECT MAX(`index`) FROM beaconDistanceMeasurement")
    int getMaxIndex();

}
