package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.PrimaryKey;

public class Beacon {
    @PrimaryKey
    private String uuid;
}
