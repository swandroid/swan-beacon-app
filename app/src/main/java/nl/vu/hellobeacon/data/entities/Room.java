package nl.vu.hellobeacon.data.entities;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Room {
    @PrimaryKey
    @NonNull
    public String roomName;

    public Room(@NonNull String roomName) {
        this.roomName = roomName;
    }
}
