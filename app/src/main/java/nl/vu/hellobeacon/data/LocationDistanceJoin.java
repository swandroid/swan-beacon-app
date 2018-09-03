package nl.vu.hellobeacon.data;

import android.arch.persistence.room.Embedded;

import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;
import nl.vu.hellobeacon.data.entities.Room;

public class LocationDistanceJoin {
    @Embedded
    public BeaconDistanceMeasurement b;

    @Embedded
    public Room r;
}
