package nl.vu.hellobeacon.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Room {
    @PrimaryKey
    @NonNull
    private String roomName;



    public String getRoomName(){
        return roomName;
    }

    public Room(@NonNull String roomName) {
        this.roomName = roomName;
    }
}
