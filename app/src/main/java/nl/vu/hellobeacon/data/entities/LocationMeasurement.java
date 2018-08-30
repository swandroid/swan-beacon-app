package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity
public class LocationMeasurement {
    @PrimaryKey(autoGenerate = true)
    private int index;

    @ColumnInfo(name = "Beacon_Distance_Measurement")
    private List<BeaconDistanceMeasurement> beaconDistanceMeasurements;

    @ColumnInfo(name = "Room")
    private Room room;
}
