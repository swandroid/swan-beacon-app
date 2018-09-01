package nl.vu.hellobeacon.data.dataAccesObjects;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import nl.vu.hellobeacon.data.entities.LocationMeasurement;

@Dao
public interface LocationMeasurementDAO {
    @Insert
    void Insert(LocationMeasurement locationMeasurement);

    @Query("SELECT * FROM beaconDistanceMeasurement INNER JOIN locationmeasurement ON beacon.")


}

