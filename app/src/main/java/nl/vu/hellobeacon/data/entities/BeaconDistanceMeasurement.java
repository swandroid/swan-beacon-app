package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(primaryKeys = {"index"},
        foreignKeys = @ForeignKey(entity = Beacon.class,
                parentColumns = "uuid",
                childColumns = "beacon",
                onDelete = CASCADE))
public class BeaconDistanceMeasurement {

    @NonNull
    public int index;

    @NonNull
    public String beacon;


    public double distance;

    public BeaconDistanceMeasurement(int index, String beacon, double distance) {
        this.index = index;
        this.beacon = beacon;
        this.distance = distance;
    }
}
