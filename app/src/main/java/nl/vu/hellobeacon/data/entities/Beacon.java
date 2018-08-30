package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Beacon {
    @PrimaryKey
    @NonNull
    private String uuid;

    public String getUuid(){
        return uuid;
    }

    public Beacon(String uuid){
        this.uuid = uuid;
    }
}
