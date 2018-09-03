package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"distanceMeasurement", "room"},
        foreignKeys = {
                @ForeignKey(entity = BeaconDistanceMeasurement.class,
                        parentColumns = "index",
                        childColumns = "distanceMeasurement",
                        onDelete = CASCADE),
                @ForeignKey(entity = Room.class,
                        parentColumns = "roomName",
                        childColumns = "room",
                        onDelete = CASCADE)
        })
public class LocationMeasurement {
    @NonNull
    public final int distanceMeasurement;

    @NonNull
    public final String room;

    public LocationMeasurement(int distanceMeasurement, String room) {
        this.distanceMeasurement = distanceMeasurement;
        this.room = room;
    }

}
