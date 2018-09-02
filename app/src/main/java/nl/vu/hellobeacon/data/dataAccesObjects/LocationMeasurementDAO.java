package nl.vu.hellobeacon.data.dataAccesObjects;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nl.vu.hellobeacon.data.entities.LocationMeasurement;

@Dao
public interface LocationMeasurementDAO {
    @Insert
    void insert(LocationMeasurement locationMeasurement);

    @Delete
    void delete(LocationMeasurement locationMeasurement);

//    @Query("SELECT * FROM locationmeasurement INNER JOIN beacondistancemeasurement " +
//            "ON locationmeasurement.distanceMeasurement=beacondistancemeasurement.`index` INNER JOIN room ON locationmeasurement.room = room.roomName")
    @Query("SELECT * FROM locationmeasurement")
    LiveData<List<LocationMeasurement>> getAll();


}

