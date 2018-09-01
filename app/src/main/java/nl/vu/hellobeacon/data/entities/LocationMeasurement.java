package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(primaryKeys = {"distanceMeasurement"},
        foreignKeys = {
                @ForeignKey(entity = BeaconDistanceMeasurement.class,
                                    parentColumns = "index",
                                    childColumns = "distanceMeasurement"),
                @ForeignKey(entity = Room.class,
                                    parentColumns = "roomName",
                                    childColumns = "room")
        })
public class LocationMeasurement {
    public final int distanceMeasurement;

    public final String room;

    public LocationMeasurement(int distanceMeasurement, String room){
        this.distanceMeasurement = distanceMeasurement;
        this.room = room;
    }

}
