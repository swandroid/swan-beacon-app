package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BeaconDistanceMeasurement {
    @PrimaryKey(autoGenerate = true)
    private int index;

    private Beacon beacon;

    private double distance;





}
