package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Beacon.class,
                                    parentColumns = "uuid",
                                    childColumns = "beacon"))
public class BeaconDistanceMeasurement {

    public int index;

    public String beacon;

    public double distance;
}
